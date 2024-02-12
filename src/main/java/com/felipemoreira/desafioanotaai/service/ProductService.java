package com.felipemoreira.desafioanotaai.service;

import com.felipemoreira.desafioanotaai.domain.category.Category;
import com.felipemoreira.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.felipemoreira.desafioanotaai.domain.product.Product;
import com.felipemoreira.desafioanotaai.domain.product.ProductDTO;
import com.felipemoreira.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.felipemoreira.desafioanotaai.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    /* ---------------------------------------------------- CRUD ---------------------------------------------------- */

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Product insert(ProductDTO productData) {
        Category categoryThisProduct = categoryService.getById(productData.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada no banco!"));

        Product newProduct = new Product(productData);
        newProduct.setCategory(categoryThisProduct);

        this.repository.save(newProduct);
        return newProduct;
    }

    public Product update(String id, ProductDTO productData) {
        Product productToBeUpdated = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (productData.categoryId() != null) this.categoryService.getById(productData.categoryId())
                .ifPresent(productToBeUpdated::setCategory);
        if (!productData.title().isEmpty()) productToBeUpdated.setTitle(productData.title());
        if (!productData.description().isEmpty()) productToBeUpdated.setDescription(productData.description());
        if (productData.price() != null) productToBeUpdated.setPrice(productData.price());

        this.repository.save(productToBeUpdated);
        return productToBeUpdated;
    }

    public void delete(String id) {
        Product productToBeDelete = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.repository.delete(productToBeDelete);
    }
}
