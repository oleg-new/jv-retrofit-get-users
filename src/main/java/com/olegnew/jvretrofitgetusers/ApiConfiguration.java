package com.olegnew.jvretrofitgetusers;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiConfiguration {
    private static final String API_BASE_URL = "https://api.stackexchange.com";
    private static final String API_VERSION_SPEC = "application/vnd.github.v3+json";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private final List<String> locationList = new ArrayList<>(
            Arrays.asList("Moldova","Romania"));
}
