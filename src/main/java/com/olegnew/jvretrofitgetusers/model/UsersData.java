package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class UsersData {

    @SerializedName("items")
    private List<Items> items;

}
