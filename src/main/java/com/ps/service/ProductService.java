package com.ps.service;

import com.ps.model.Product;
import com.ps.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProduct() {
        log.info("get all product from database");
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        log.info("save product in database");
        return productRepo.save(product);
    }

    public Product updateProduct(String productId, Product product) {
        log.info("update product in database");
        Optional<Product> findById = productRepo.findById(productId);
        if (findById.isPresent()) {

            if (product.getProductName() != null)
                findById.get().setProductName(product.getProductName());

            if (product.getPrice() != 0)
                findById.get().setPrice(product.getPrice());

            productRepo.save(findById.get());

            return findById.get();
        }

        log.error("Product Id not found id: {}", productId);
        throw new RuntimeException("Product Id not found !!!");
    }

    public void removeProduct(String productId) {
        log.info("delete product from database");
        Optional<Product> findById = productRepo.findById(productId);

        if (findById.isPresent()) {
            productRepo.delete(findById.get());
        } else {
            log.error("Product Id not found id: {}", productId);
            throw new RuntimeException("Product Id not found !!!");
        }

    }

    public Product getProductBuId(String productId) {
        log.info("get product from database id: {}", productId);
        Optional<Product> findById = productRepo.findById(productId);

        if (findById.isPresent()) {
            return findById.get();
        }

        log.error("Product Id not found id: {}", productId);
        throw new RuntimeException("Product Id not found !!!");
    }
}
