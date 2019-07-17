package com.tw.apistackbase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CriminalCase {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;
    @NotNull
    private long incidentTime;

    public CriminalCase() {}

    public CriminalCase(@NotNull String name, @NotNull long incidentTime) {
        this.name = name;
        this.incidentTime = incidentTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(long incidentTime) {
        this.incidentTime = incidentTime;
    }
}
