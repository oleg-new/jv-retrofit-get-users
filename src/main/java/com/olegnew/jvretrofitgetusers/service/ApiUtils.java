package com.olegnew.jvretrofitgetusers.service;

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/";

    public static ItemService getItemService() {
        return RetrofitClient.getClient(BASE_URL).create(ItemService.class);
    }
}
