package com.hanteo.board.unit;

import com.hanteo.board.domain.category.dto.CategoryResponse;
import com.hanteo.board.domain.category.models.Category;
import com.hanteo.board.domain.category.repository.CategoryRepositoryCustom;
import com.hanteo.board.domain.category.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepositoryCustom categoryRepository;

    @Test
    void getAllCategories() {
        Category category = new Category(1L, 2L,"test_nm");
        when(categoryRepository.findAllCategories()).thenReturn(Collections.singletonList(category));

        List<CategoryResponse> result = categoryService.getAllCategories();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getParent_idx()).isEqualTo(1L);
    }

    @Test
    void getCategoryById() {
        Category category = new Category(1L, 2L,"test_nm");
        when(categoryRepository.findCategoryById(1L)).thenReturn(category);

        CategoryResponse result = categoryService.getCategoryById(1L);

        assertThat(result.getParent_idx()).isEqualTo(1L);
        assertThat(result.getChild_id()).isEqualTo(2L);
    }
}