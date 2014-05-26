package com.netflix.suro;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by thinker0 on 2014. 5. 26..
 *
 * @author thinker0
 */
@JsonRootName("details")
public class SuroInstanceDetails {
    private String description;

    public SuroInstanceDetails() {
        this("");
    }

    public SuroInstanceDetails(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
