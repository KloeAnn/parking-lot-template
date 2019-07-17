package com.tw.apistackbase.model;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private SpecificInformation specificInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Procuratorate procuratorate;

    public CriminalCase() {}

    public CriminalCase(@NotNull String name, @NotNull long incidentTime, SpecificInformation specificInformation) {
        this.name = name;
        this.incidentTime = incidentTime;
        this.specificInformation = specificInformation;
    }

    public CriminalCase(@NotNull String name, @NotNull long incidentTime, SpecificInformation specificInformation, @NotNull Procuratorate procuratorate) {
        this.name = name;
        this.incidentTime = incidentTime;
        this.specificInformation = specificInformation;
        this.procuratorate = procuratorate;
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

    public SpecificInformation getSpecificInformation() {
        return specificInformation;
    }

    public void setSpecificInformation(SpecificInformation specificInformation) {
        this.specificInformation = specificInformation;
    }

    public Procuratorate getProcuratorate() {
        return procuratorate;
    }

    public void setProcuratorate(Procuratorate procuratorate) {
        this.procuratorate = procuratorate;
    }
}
