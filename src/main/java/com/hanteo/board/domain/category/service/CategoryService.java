package com.hanteo.board.domain.category.service;

import com.hanteo.board.domain.category.dto.CategoryResponse;
import com.hanteo.board.domain.category.models.Category;
import com.hanteo.board.domain.category.repository.CategoryRepositoryCustom;
import com.hanteo.board.domain.category.repository.JpaCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepositoryCustom categoryRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryService(CategoryRepositoryCustom categoryRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    public List<CategoryResponse> getCategoryWithSubcategories(Long parentId) {
        CategoryResponse parentCategory = getCategoryById(parentId);

        List<Category> subcategories = categoryRepository.findSubcategoriesById(parentId);
        List<CategoryResponse> subcategoryResponses = subcategories.stream()
                .map(this::toResponse)
                .toList();

        List<CategoryResponse> result = new ArrayList<>();
        result.add(parentCategory);

        for (CategoryResponse subcategory : subcategoryResponses) {
            List<CategoryResponse> subSubcategories = getCategoryWithSubcategories(subcategory.getChild_id());
            result.addAll(subSubcategories);
        }

        return result;
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

    public List<CategoryResponse> getCategoryByCategoryNm(String categoryNm){
        return jpaCategoryRepository.findByCategoryNmContaining(categoryNm).stream()
                .map(this::toResponse)
                .toList();
    }

    private CategoryResponse toResponse(Category category) {
        if (category == null) {
            throw new NullPointerException("해당하는 카테고리가 없습니다.");
        }

        return CategoryResponse.builder()
                .parent_idx(category.getParentIdx())
                .child_id(category.getChildId())
                .category_nm(category.getCategoryNm())
                .build();
    }

}
