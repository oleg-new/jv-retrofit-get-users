package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BadgeCounts {

    @SerializedName("bronze")
    private int bronze;

    @SerializedName("silver")
    private int silver;

    @SerializedName("gold")
    private int gold;

    @Override
    public String toString() {
        return "BadgeCounts{"
              + "\n                              bronze=" + bronze
              + "\n                              silver=" + silver
              + "\n                              gold=" + gold
              + "\n                              }";
    }
}
