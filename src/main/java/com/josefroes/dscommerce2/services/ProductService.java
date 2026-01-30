package com.josefroes.dscommerce2.services;

import com.josefroes.dscommerce2.dto.ProductDTO;
import com.josefroes.dscommerce2.entities.Product;
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

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {

        Product product = new Product();
        copyDtoToEntity(productDTO, product);

        product = repository.save(product);

        return  new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {

        Product product = repository.getReferenceById(id);
        copyDtoToEntity(productDTO, product);

        product = repository.save(product);

        return  new ProductDTO(product);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
