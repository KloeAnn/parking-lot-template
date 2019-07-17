package com.tw.apistackbase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Procuratorate {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(length = 50, name = "ProcuratorateName")
    private String name;

    public Procuratorate() {}

    public Procuratorate(@NotNull String name) {
        this.name = name;
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
}
