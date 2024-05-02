package com.neurogene.store.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {
    private String id;
    @NotBlank(message = "Category name is mandatory")
    private String name;
    private String description;
}

