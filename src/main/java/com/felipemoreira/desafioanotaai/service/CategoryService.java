package com.felipemoreira.desafioanotaai.service;

import com.felipemoreira.desafioanotaai.domain.category.Category;
import com.felipemoreira.desafioanotaai.domain.category.CategoryDTO;
import com.felipemoreira.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.felipemoreira.desafioanotaai.repository.CategoryRepository;
import com.felipemoreira.desafioanotaai.service.aws.AwsSnsService;
import com.felipemoreira.desafioanotaai.service.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    /* ---------------------------------------------------- CRUD ---------------------------------------------------- */

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category insert(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        this.snsService.publish(new MessageDTO(newCategory.toString()));
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category categoryToBeUpdated = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) categoryToBeUpdated.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) categoryToBeUpdated.setDescription(categoryData.description());

        this.repository.save(categoryToBeUpdated);
        this.snsService.publish(new MessageDTO(categoryToBeUpdated.toString()));

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
