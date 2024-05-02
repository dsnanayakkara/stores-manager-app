package com.neurogene.store.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "categories")
public class Category {
    @Id
    private String id;
    private String name;
    private String description;

    public Category() {
    }


}