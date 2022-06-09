package com.example.EMSbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class HttpSecurityDisabler extends WebSecurityConfigurerAdapter {

    public HttpSecurityDisabler() {
        super(true); // Disable defaults
    }

    @Override
    protected void configure(HttpSecurity http) {
        // Do nothing, this is just overriding the default behavior in WebSecurityConfigurerAdapter
    }
}
