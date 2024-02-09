package com.felipemoreira.desafioanotaai.controllers;

import com.felipemoreira.desafioanotaai.domain.product.Product;
import com.felipemoreira.desafioanotaai.domain.product.ProductDTO;
import com.felipemoreira.desafioanotaai.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {
        Product newProduct = service.insert(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathParam("id") String id, @RequestBody ProductDTO productData) {
        Product updatedProduct = service.update(id, productData);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathParam("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
