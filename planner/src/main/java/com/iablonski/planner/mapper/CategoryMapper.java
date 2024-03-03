package com.iablonski.planner.mapper;

import com.iablonski.planner.domain.Category;
import com.iablonski.planner.dto.CategoryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);
    @InheritInverseConfiguration
    CategoryDTO toDTO(Category category);
}
