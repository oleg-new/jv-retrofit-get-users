package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ExternalLinks {

    @SerializedName("type")
    private String type;

    @SerializedName("link")
    private String link;

    @Override
    public String toString() {
        return "ExternalLinks{"
              + "\n                              type='" + type + '\''
              + "\n                              link='" + link + '\''
              + "\n                              } \n ";
    }
}
