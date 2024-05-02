package com.neurogene.store.app.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class StoreDTO {
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String address;

    private String humanReadableAddress;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Image is mandatory")
    private String image;

    private CategoryDTO category;

    private GeoLocationDTO geoLocation;

}
