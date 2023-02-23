package com.olegnew.jvretrofitgetusers.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class Items {

    @SerializedName("badge_counts")
    private BadgeCounts badgeCounts;

    @SerializedName("collectives")
    private List<Collectives> collectives;

    @SerializedName("account_id")
    private Long accountId;

    @SerializedName("is_employee")
    private boolean isEmployee;

    @SerializedName("last_modified_date")
    private int lastModifiedDate;

    @SerializedName("last_access_date")
    private int lastAccessDate;

    @SerializedName("reputation_change_year")
    private int reputationChangeYear;

    @SerializedName("reputation_change_quarter")
    private int reputationChangeQuarter;

    @SerializedName("reputation_change_month")
    private int reputationChangeMonth;

    @SerializedName("reputation_change_week")
    private int reputationChangeWeek;

    @SerializedName("reputation_change_day")
    private int reputationChangeDay;

    @SerializedName("reputation")
    private int reputation;

    @SerializedName("creation_date")
    private int creationDate;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("user_id")
    private Long userId;

    @SerializedName("location")
    private String location;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("link")
    private String link;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("display_name")
    private String displayName;
    private int answerCount;
    private int questionCount;

    @Override
    public String toString() {
        return "Items   {"
                + "\n                             displayName='" + displayName + '\''
                + "\n                             location='" + location + '\''
                + "\n                             answerCount=" + answerCount
                + "\n                             questionCount=" + questionCount
                + "\n                             userId=" + userId
                + "\n                             collectives=" + collectives
                + "\n                             link='" + link + '\''
                + "\n                             profileImage='" + profileImage + '\''
                + "\n                             isEmployee=" + isEmployee
              + "\n                             } \n ";
    }

    public String toStringWithAllFields() {
        return "Items   userId=" + userId + " {"
                + "\n                       displayName='" + displayName + '\''
                + "\n                       userId=" + userId
                + "\n                       location='" + location + '\''
                + "\n                       badgeCounts=" + badgeCounts
                + "\n                       collectives=" + collectives
                + "\n                       accountId=" + accountId
                + "\n                       isEmployee=" + isEmployee
                + "\n                       lastModifiedDate=" + lastModifiedDate
                + "\n                       lastAccessDate=" + lastAccessDate
                + "\n                       reputationChangeYear=" + reputationChangeYear
                + "\n                       reputationChangeQuarter=" + reputationChangeQuarter
                + "\n                       reputationChangeMonth=" + reputationChangeMonth
                + "\n                       reputationChangeWeek=" + reputationChangeWeek
                + "\n                       reputationChangeDay=" + reputationChangeDay
                + "\n                       reputation=" + reputation
                + "\n                       creationDate=" + creationDate
                + "\n                       userType='" + userType + '\''
                + "\n                       websiteUrl='" + websiteUrl + '\''
                + "\n                       link='" + link + '\''
                + "\n                       profileImage='" + profileImage + '\''
                + "\n                       } \n ";
    }
}
