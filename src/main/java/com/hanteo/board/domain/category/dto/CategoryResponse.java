package com.hanteo.board.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {

    private final long parent_idx;
    private final long child_id;
    private final String category_nm;
    public CategoryResponse(long parent_idx, long child_id,String category_nm) {
        this.parent_idx = parent_idx;
        this.child_id = child_id;
        this.category_nm = category_nm;
    }
}