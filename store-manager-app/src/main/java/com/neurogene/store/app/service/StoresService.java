package com.neurogene.store.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neurogene.store.app.domain.Store;
import com.neurogene.store.app.dto.StoreDTO;
import com.neurogene.store.app.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoresService {
    private static final Logger LOG = LoggerFactory.getLogger(StoresService.class);
    private final StoreRepository storeRepository;

    @Autowired
    public StoresService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store addStore(Store store) {
        return null;
    }

    public Store updateStore(String storeId, Store storeDetails) {
        return null;
    }

    public void deleteStore(String storeId) {

    }

    public Page<StoreDTO> findStores(Pageable pageable, String name, String category) {
        // first filter by name (if name is present in the request)
        Page<Store> page = storeRepository.findByNameIfPresent(name, pageable);
        LOG.info("total stores received from DB {}", page.getTotalElements());
        ObjectMapper objectMapper = new ObjectMapper();

        // filter by category (if category is present in the request)
        List<StoreDTO> filteredStores = page.getContent().stream()
                .filter(store -> category == null || (store.getCategory() != null && store.getCategory().getName().equalsIgnoreCase(category)))
                .map(store -> objectMapper.convertValue(store, StoreDTO.class))
                .collect(Collectors.toList());

        LOG.info("total stores after filtering {}", filteredStores.size());

        return new PageImpl<>(filteredStores, pageable, filteredStores.size());
    }
}
