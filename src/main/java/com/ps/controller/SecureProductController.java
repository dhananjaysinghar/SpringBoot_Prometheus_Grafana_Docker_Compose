package com.ps.controller;

import com.ps.model.Product;
import com.ps.model.ProductVo;
import com.ps.model.Response;
import com.ps.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/secure")
@CrossOrigin
public class SecureProductController {

    @Autowired
    private ProductService service;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestHeader("Authorization") String authorizationToken,
                                              @RequestBody ProductVo productData) {
        Product product = new Product();
        BeanUtils.copyProperties(productData, product);
        Product addProduct = service.addProduct(product);
        return ResponseEntity.ok(addProduct);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@RequestHeader("Authorization") String authorizationToken,
                                           @PathVariable String productId, @RequestBody ProductVo productData) {

        Product product = new Product();
        BeanUtils.copyProperties(productData, productData);
        product.setProductId(productId);
        try {
            return ResponseEntity.ok(service.updateProduct(product.getProductId(), product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@RequestHeader("Authorization") String authorizationToken,
                                           @PathVariable String productId) {

        try {
            service.removeProduct(productId);
            Response resp = new Response();
            resp.setStatus("Product deleted successfully");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
