package com.ps.controller;

import com.ps.model.Product;
import com.ps.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(service.getAllProduct());
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable String productId) {
        try {
            return ResponseEntity.ok(service.getProductBuId(productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
