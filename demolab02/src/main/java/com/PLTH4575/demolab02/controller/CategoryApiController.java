package com.PLTH4575.demolab02.controller;

import com.PLTH4575.demolab02.model.Category;
import com.PLTH4575.demolab02.repository.CategoryRepository;
import com.PLTH4575.demolab02.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @PostMapping("/add")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found on :: "
                        + id));
        return ResponseEntity.ok().body(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                 @RequestBody Category categoryDetails) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found on :: "
                        + id));
        category.setName(categoryDetails.getName());
        category.setThumbnail(categoryDetails.getThumbnail());
        final Category updatedCategory =categoryService.addCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: "
                        + id));
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
