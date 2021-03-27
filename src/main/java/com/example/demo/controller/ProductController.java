package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private Environment environment;

    @ModelAttribute("listCategory")
    public List<Category> listCate() {
        return categoryService.findAll();
    }

    //Upload File
    private void uploadFile(@ModelAttribute("product") Product product) {
        MultipartFile multipartFile = product.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        String newFile = fileUpload + fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage(fileName);
    }

    //Show All
    @GetMapping("")
    private ModelAndView showAll(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/shop");
        List<Category> categories = categoryService.findAll();
        Page<Product> productPage = productService.findAll(pageable);
        Long numberOfProducts = productPage.getTotalElements();
        modelAndView.addObject("products",productPage );
        modelAndView.addObject("categoriesProduct",categories);
        modelAndView.addObject("numberOfProducts",numberOfProducts );
        return modelAndView;
    }

    @GetMapping("/manager")
    private ModelAndView showAllFormAdmin(@PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", productService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    private ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    private ModelAndView create(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("product/create");
        uploadFile(product);
        productService.save(product);
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Tao moi thanh cong");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    private ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    private ModelAndView edit(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        uploadFile(product);
        productService.save(product);
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Sua thanh cong");
        return modelAndView;
    }

    @GetMapping ("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products/manager");
        productService.remove(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("view/shop-detail");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    //SearchNameProduct
    @PostMapping("/search")
    public ModelAndView showSearchNameProduct(@RequestParam String name, @PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/shop");
        String nameProduct = "%" + name + "%";
        Page<Product> productList = productService.findProductByName(nameProduct, pageable);
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

<<<<<<< HEAD
=======
    @PostMapping("/searchCategory")
    public ModelAndView searchProductByCategory(@RequestParam Long id, @PageableDefault(size = 6) Pageable pageable) {
        Page<Product> productPage = productService.findProductByCategoryName(id, pageable);
        return new ModelAndView("view/shop", "products", productPage);
    }

    @GetMapping("/top5priceMax")
    public ModelAndView find5PriceMax(@PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/shop");
        modelAndView.addObject("top5price", productService.findTop5ByOrderByPriceDesc(pageable));
        return modelAndView;
    }
>>>>>>> demo-of-khai

}
