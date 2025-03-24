package com.hanteo.board.api;

import com.hanteo.board.common.ResponseApi;
import com.hanteo.board.domain.category.dto.CategoryResponse;
import com.hanteo.board.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 전체 조회
    @Operation(summary = "모든 카테고리 조회", description = "전체 카테고리 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 목록 조회 성공")
    })
    @GetMapping("/getCategories")
    public ResponseEntity<ResponseApi<List<CategoryResponse>>> getCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ResponseApi.success(categories));
    }

    // ID로 조회
    @Operation(summary = "ID로 카테고리 조회", description = "카테고리 ID로 해당 카테고리를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 조회 성공"),
            @ApiResponse(responseCode = "404", description = "카테고리 미발견")
    })
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<ResponseApi<List<CategoryResponse>>>  getCategoryById(@PathVariable("id") Long id) {
        List<CategoryResponse> categories = categoryService.getCategoryWithSubcategories(id);
        return ResponseEntity.ok(ResponseApi.success(categories));
    }

    // 카테고리명으로 조회
    @Operation(summary = "카테고리명으로 카테고리 조회", description = "카테고리명으로 해당 카테고리를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 조회 성공"),
            @ApiResponse(responseCode = "404", description = "카테고리 미발견")
    })
    @GetMapping("/getCategoryByCategoryNm/{categoryNm}")
    public ResponseEntity<ResponseApi<List<CategoryResponse>>> getCategoryByCategoryNm(@PathVariable("categoryNm") String categoryNm) {
        List<CategoryResponse> categories = categoryService.getCategoryByCategoryNm(categoryNm);
        return ResponseEntity.ok(ResponseApi.success(categories));
    }

}