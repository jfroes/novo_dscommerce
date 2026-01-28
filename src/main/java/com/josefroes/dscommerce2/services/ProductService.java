package com.josefroes.dscommerce2.services;

import com.josefroes.dscommerce2.dto.ProductDTO;
import com.josefroes.dscommerce2.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        return repository.findById(id).map(ProductDTO::new).orElseThrow(() -> new EntityNotFoundException("produto não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return  repository.findAll(pageable).map(ProductDTO::new);
    }
}
