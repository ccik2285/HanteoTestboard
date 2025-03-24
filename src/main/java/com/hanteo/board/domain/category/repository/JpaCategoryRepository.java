package com.hanteo.board.domain.category.repository;

import com.hanteo.board.domain.category.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByCategoryNmContaining(String categoryNm);
}
