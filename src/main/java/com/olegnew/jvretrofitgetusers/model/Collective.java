package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class Collective {

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("external_links")
    private List<ExternalLinks> externalLinks;

    @SerializedName("description")
    private String description;

    @SerializedName("link")
    private String link;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    @Override
    public String toString() {
        return "Collective{"
                + "\n                              tags=" + tags
                + "\n                              } \n ";
    }

    public String toStringWithAllFields() {
        return "Collective{"
                + "\n                              tags=" + tags
                + "\n                             externalLinks=" + externalLinks
                + "\n                             description='" + description + '\''
                + "\n                             link='" + link + '\''
                + "\n                             name='" + name + '\''
                + "\n                             slug='" + slug + '\''
                + "\n                              } \n ";
    }
}
