package com.hanteo.board.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {

    private final long parent_idx;
    private final long child_id;

    public CategoryResponse(long parent_idx, long child_id) {
        this.parent_idx = parent_idx;
        this.child_id = child_id;
    }
}