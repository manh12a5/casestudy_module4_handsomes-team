package com.example.demo.service;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<E> {

    List<E> findAll();

    E findById(Long id);

    E save(E e);

    void remove(Long id);

}