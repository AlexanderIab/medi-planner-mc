package com.iablonski.planner.service;

import com.iablonski.planner.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getCategoryById(String id);
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategoryById(String id);
    List<CategoryDTO> getCategoriesByUserId(String userId);
    List<CategoryDTO> findByTitle(String title);
}
