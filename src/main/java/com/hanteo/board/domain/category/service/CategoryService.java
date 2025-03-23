package com.hanteo.board.domain.category.service;

import com.hanteo.board.domain.category.dto.CategoryResponse;
import com.hanteo.board.domain.category.models.Category;
import com.hanteo.board.domain.category.repository.CategoryRepositoryCustom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepositoryCustom categoryRepository;

    public CategoryService(CategoryRepositoryCustom categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllCategories().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findCategoryById(id);
        return toResponse(category);
    }

    private CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .parent_idx(category.getParentIdx())
                .child_id(category.getChildId())
                .build();
    }
}
