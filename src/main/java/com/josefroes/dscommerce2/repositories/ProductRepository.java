package com.josefroes.dscommerce2.repositories;

import com.josefroes.dscommerce2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
