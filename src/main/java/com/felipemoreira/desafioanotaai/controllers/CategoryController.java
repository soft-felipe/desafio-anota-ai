package com.felipemoreira.desafioanotaai.controllers;

import com.felipemoreira.desafioanotaai.domain.category.Category;
import com.felipemoreira.desafioanotaai.domain.category.CategoryDTO;
import com.felipemoreira.desafioanotaai.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData) {
        Category newCategory = service.insert(categoryData);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData) {
        Category updatedCategory = service.update(id, categoryData);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
