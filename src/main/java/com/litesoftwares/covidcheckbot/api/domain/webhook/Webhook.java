package com.litesoftwares.covidcheckbot.api.domain.webhook;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Webhook {
    @JsonProperty("id")
    private long id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("created_timestamp")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Webhook{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", valid=" + valid +
                ", createdAt=" + createdAt +
                '}';
    }
}

