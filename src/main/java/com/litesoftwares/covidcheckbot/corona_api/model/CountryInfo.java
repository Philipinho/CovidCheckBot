package com.litesoftwares.covidcheckbot.corona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInfo {
    @JsonProperty("_id")
    private Long id;
    @JsonProperty("lat")
    private Long lat;
    @JsonProperty("long")
    private Long _long;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("iso3")
    private String iso3;
    @JsonProperty("iso2")
    private String iso2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long get_long() {
        return _long;
    }

    public void set_long(Long _long) {
        this._long = _long;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public String toString() {
        return "CountryInfo{" +
                "id=" + id +
                ", lat=" + lat +
                ", _long=" + _long +
                ", flag='" + flag + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", iso2='" + iso2 + '\'' +
                '}';
    }
}
