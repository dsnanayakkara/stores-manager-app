package com.neurogene.store.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoreDTOPage {
    private List<StoreDTO> content;
    private int number;
    private int size;
    private int totalPages;
}