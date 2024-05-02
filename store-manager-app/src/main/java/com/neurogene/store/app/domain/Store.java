package com.neurogene.store.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "stores")
public class Store {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String humanReadableAddress;
    private String description;
    private String image;

    @DBRef
    private Category category;

    @Field("geolocation")
    private GeoLocation geoLocation;

    @Setter
    @Getter
    public static class GeoLocation {
        private String type;
        private double[] coordinates;
    }

    public Store() {
    }

}
