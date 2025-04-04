package com.projetolivraria.livraria.controller;

import com.projetolivraria.livraria.model.Category;
import com.projetolivraria.livraria.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/new")
    public ResponseEntity<Category> newCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(service.newCategory(category));
        } catch (RuntimeException e) {
            System.out.println("Error while trying to create category" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public Iterable<Category> AllCategory() {
        try {
            return service.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while trying to fetch data" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Category findCategoryById(@PathVariable int id) {
        try {
            return service.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while trying to fetch data" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable int id, @RequestBody Category c) {
        try {
            return ResponseEntity.ok(service.editCategory(c));
        }  catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Data not found with ID: " + id);
        } catch (Exception e ){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred while trying to fetch data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable int id) {
        try {
            service.deleteCategory(id);
            System.out.println(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while trying to fetch data" + e.getMessage());
        }
    }
}
