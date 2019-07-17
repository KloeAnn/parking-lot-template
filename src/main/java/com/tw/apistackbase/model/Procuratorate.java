package com.tw.apistackbase.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Procuratorate {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(length = 50, name = "ProcuratorateName")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prosecutor> prosecutorList;

    public Procuratorate() {}

    public Procuratorate(@NotNull String name, List<Prosecutor> prosecutorList) {
        this.name = name;
        this.prosecutorList = prosecutorList;
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

    public List<Prosecutor> getProsecutorList() {
        return prosecutorList;
    }

    public void setProsecutorList(List<Prosecutor> prosecutorList) {
        this.prosecutorList = prosecutorList;
    }
}
