package com.felipemoreira.desafioanotaai.service;

import com.felipemoreira.desafioanotaai.domain.category.Category;
import com.felipemoreira.desafioanotaai.domain.category.CategoryDTO;
import com.felipemoreira.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.felipemoreira.desafioanotaai.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    /* ---------------------------------------------------- CRUD ---------------------------------------------------- */

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category insert(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category categoryToBeUpdated = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) categoryToBeUpdated.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) categoryToBeUpdated.setDescription(categoryData.description());

        this.repository.save(categoryToBeUpdated);
        return categoryToBeUpdated;
    }

    public void delete(String id) {
        Category categoryToBeDelete = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(categoryToBeDelete);
    }

    /* --------------------------------------------------- FUNCS ---------------------------------------------------- */

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }
}
