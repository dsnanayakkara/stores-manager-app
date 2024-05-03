package com.neurogene.store.app.controller;

import com.neurogene.store.app.domain.Store;
import com.neurogene.store.app.dto.StoreDTO;
import com.neurogene.store.app.dto.StoreDTOPage;
import com.neurogene.store.app.service.StoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Store Manager API", description = "API for Managing food stores")
@RestController
@RequestMapping("/api/stores")
public class StoresController {
    private final StoresService storeService;

    @Autowired
    public StoresController(StoresService storeService) {
        this.storeService = storeService;
    }

    @ApiOperation(value = "Get a page of stores", response = StoreDTOPage.class)
    @GetMapping("")
    public ResponseEntity<Page<StoreDTO>> getStores(
            @ApiParam(value = "Page number to return", defaultValue = "0") @RequestParam(value = "page", defaultValue = "0") int page,
            @ApiParam(value = "Number of records per page", defaultValue = "10") @RequestParam(value = "size", defaultValue = "10") int size,
            @ApiParam(value = "Name of the store") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Category of the store") @RequestParam(value = "category", required = false) String category) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StoreDTO> storePage = storeService.findStores(pageable, name, category);
        return ResponseEntity.ok(storePage);
    }

    @ApiOperation(value = "Add a new store", response = Store.class)
    @PostMapping("")
    public ResponseEntity<Store> addStore(@ApiParam(value = "Store object to return", required = true) @Valid @RequestBody Store store) {
        Store savedStore = storeService.addStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing store", response = Store.class)
    @PutMapping("/{storeId}")
    public ResponseEntity<Store> updateStore(@ApiParam(value = "ID of the store to update", required = true) @PathVariable String storeId, @ApiParam(value = "Updated store object", required = true) @Valid @RequestBody Store storeDetails) {
        Store updatedStore = storeService.updateStore(storeId, storeDetails);
        return ResponseEntity.ok(updatedStore);
    }

    @ApiOperation(value = "Delete a store")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Store> deleteStore(@ApiParam(value = "ID of the store to delete", required = true) @PathVariable String storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.ok().build();
    }
}
