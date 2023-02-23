package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Collectives {

    @SerializedName("collective")
    private Collective collective;

    @SerializedName("role")
    private String role;

    @Override
    public String toString() {
        return "Collectives{"
              + "\n                             collective=" + collective
              + "\n                              } \n ";
    }

    public String toStringWithAllFields() {
        return "Collectives{"
                + "\n                             collective=" + collective
                + "\n                             role='" + role + '\''
                + "\n                              } \n ";
    }
}

