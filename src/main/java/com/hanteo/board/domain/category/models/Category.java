package com.hanteo.board.domain.category.models;

import com.hanteo.board.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @Column(name = "parent_idx")
    private long parentIdx;

    @Column(name = "child_id")
    private long childId;

    @Column(name = "category_nm")
    private String categoryNm;

    @Builder
    public Category(long parentIdx, long childId,String categoryNm) {
        this.parentIdx = parentIdx;
        this.childId = childId;
        this.categoryNm = categoryNm;
    }
}
