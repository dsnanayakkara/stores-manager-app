package com.neurogene.store.app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GeoLocationDTO {
    @NotNull(message = "Geolocation type is mandatory")
    private String type;

    @Size(min = 2, max = 2, message = "Coordinates should have a size of 2")
    private double[] coordinates;

}
