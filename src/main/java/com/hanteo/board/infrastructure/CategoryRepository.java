package com.hanteo.board.infrastructure;

import com.hanteo.board.domain.category.models.Category;
import com.hanteo.board.domain.category.models.QCategory;
import com.hanteo.board.domain.category.repository.CategoryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QCategory category = QCategory.category;

    public CategoryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Category> findAllCategories() {
        return queryFactory.selectFrom(category).fetch();
    }

    @Override
    public Category findCategoryById(Long id) {
        return queryFactory.selectFrom(category)
                .where(category.parentIdx.eq(id))
                .fetchOne();
    }
}
