package com.example.license.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Getter
//@Setter
//public class ServiceConfig {
    //@Value("${example.property}")
    //private String exampleProperty;

    //@Value("${redis.server}")
    //private String redisServer="";

    //@Value("${redis.port}")
    //private String redisPort="";
//}

@Configuration
@ConfigurationProperties(prefix = "example")
@Getter
@Setter
public class ServiceConfig{
    private String property;
}