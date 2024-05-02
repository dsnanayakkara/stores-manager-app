package com.neurogene.store.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "storemanager.common")
public class StoreManagerPropertyConfig {

    private String baseUrl;



}
