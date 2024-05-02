package com.neurogene.store.app.controller;

import com.neurogene.store.app.domain.Store;
import com.neurogene.store.app.dto.StoreDTO;
import com.neurogene.store.app.service.StoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Get a list of stores", notes = "Get a list of all stores with optional keyword filtering and pagination support")
    @GetMapping("")
    public ResponseEntity<Page<StoreDTO>> getStores(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StoreDTO> storePage = storeService.findStores(pageable, name, category);
        return ResponseEntity.ok(storePage);
    }

    @PostMapping("")
    public ResponseEntity<Store> addStore(@Valid @RequestBody Store store) {
        Store savedStore = storeService.addStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Store> updateStore(@PathVariable String storeId, @Valid @RequestBody Store storeDetails) {
        Store updatedStore = storeService.updateStore(storeId, storeDetails);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Store> deleteStore(@PathVariable String storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.ok().build();
    }

}
