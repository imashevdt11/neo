package com.imashevdt11.store.commons;


public final class EndpointConstants {

    public static final String API_PREFIX = "/api";

    public static final String PRODUCTS_ENDPOINT = API_PREFIX + "/products";

    public static final String AUTH_ENDPOINT = API_PREFIX + "/auth";

    public static final String[] WHITE_LIST_URL = {
            AUTH_ENDPOINT + "/register/user",
            AUTH_ENDPOINT + "/authenticate",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    private EndpointConstants() {}
}