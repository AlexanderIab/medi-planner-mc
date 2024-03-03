package com.iablonski.planner.service.serviceImpl;

import com.iablonski.planner.domain.Category;
import com.iablonski.planner.dto.CategoryDTO;
import com.iablonski.planner.mapper.CategoryMapper;
import com.iablonski.planner.repository.CategoryRepo;
import com.iablonski.planner.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        return categoryMapper.toDTO(category);
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        if(categoryRepo.existsByTitle(categoryDTO.title())){
            throw new RuntimeException("Already Exists");
        }
        Category category = new Category();
        category.setTitle(categoryDTO.title());
        category.setCompletedCount(0L);
        category.setUncompletedCount(0L);
        category.setUserId(categoryDTO.userId());
        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepo.findById(categoryDTO.id()).orElseThrow();
        category.setTitle(categoryDTO.title());
        category.setCompletedCount(categoryDTO.completedCount());
        category.setUncompletedCount(categoryDTO.uncompletedCount());
        category.setUserId(categoryDTO.userId());
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategoryById(String id) {
        if (!categoryRepo.existsById(id)){
            throw new RuntimeException("Not Exists");
        }
        categoryRepo.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getCategoriesByUserId(String userId) {
        return categoryRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findByTitle(String title) {
        return categoryRepo.findByTitle(title).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}