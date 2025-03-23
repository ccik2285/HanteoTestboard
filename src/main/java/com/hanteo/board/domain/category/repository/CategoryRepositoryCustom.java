package com.hanteo.board.domain.category.repository;

import com.hanteo.board.domain.category.models.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
}
