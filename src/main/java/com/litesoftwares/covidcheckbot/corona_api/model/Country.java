package com.litesoftwares.covidcheckbot.corona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryInfo")
    private CountryInfo countryInfo;
    @JsonProperty("cases")
    private String cases;
    @JsonProperty("todayCases")
    private String todayCases;
    @JsonProperty("deaths")
    private String deaths;
    @JsonProperty("todayDeaths")
    private String todayDeaths;
    @JsonProperty("recovered")
    private String recovered;
    @JsonProperty("active")
    private String active;
    @JsonProperty("critical")
    private String critical;
    @JsonProperty("casesPerOneMillion")
    private Double casesPerOneMillion;
    @JsonProperty("deathsPerOneMillion")
    private String deathsPerOneMillion;
    @JsonProperty("updated")
    private String updated;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public Double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(Double casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(String deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", countryInfo=" + countryInfo +
                ", cases=" + cases +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", critical=" + critical +
                ", casesPerOneMillion=" + casesPerOneMillion +
                ", deathsPerOneMillion=" + deathsPerOneMillion +
                ", updated=" + updated +
                '}';
    }
}
