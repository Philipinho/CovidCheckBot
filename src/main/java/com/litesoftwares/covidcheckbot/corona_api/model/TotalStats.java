package com.litesoftwares.covidcheckbot.corona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalStats {

    @JsonProperty("cases")
    private String cases;
    @JsonProperty("todayCases")
    private String newCases;
    @JsonProperty("active")
    private String activeCases;
    @JsonProperty("deaths")
    private String deaths;
    @JsonProperty("todayDeaths")
    private String newDeaths;
    @JsonProperty("recovered")
    private String recovered;
    @JsonProperty("updated")
    private String updated;
    @JsonProperty("affectedCountries")
    private String affectedCountries;
    @JsonProperty("tests")
    private String tests;

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(String affectedCountries) {
        this.affectedCountries = affectedCountries;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "TotalStats{" +
                "cases='" + cases + '\'' +
                ", newCases='" + newCases + '\'' +
                ", activeCases='" + activeCases + '\'' +
                ", deaths='" + deaths + '\'' +
                ", newDeaths='" + newDeaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", updated='" + updated + '\'' +
                ", affectedCountries=" + affectedCountries +
                ", tests=" + tests +
                '}';
    }
}
