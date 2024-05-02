package com.neurogene.store.app.repository;

import com.neurogene.store.app.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<Store> findByName(String name, Pageable pageable);

    // if name is present, filter by name, otherwise return all
    default Page<Store> findByNameIfPresent(String name, Pageable pageable) {
        if (name != null) {
            return findByName(name, pageable);
        } else {
            return findAll(pageable);
        }
    }
}

