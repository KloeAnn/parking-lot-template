package com.tw.apistackbase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SpecificInformation {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String ObjectiveDescription;
    @NotNull
    private String SubjectiveDescription;

    public SpecificInformation() {}

    public SpecificInformation(String objectiveDescription, String subjectiveDescription) {
        ObjectiveDescription = objectiveDescription;
        SubjectiveDescription = subjectiveDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjectiveDescription() {
        return ObjectiveDescription;
    }

    public void setObjectiveDescription(String objectiveDescription) {
        ObjectiveDescription = objectiveDescription;
    }

    public String getSubjectiveDescription() {
        return SubjectiveDescription;
    }

    public void setSubjectiveDescription(String subjectiveDescription) {
        SubjectiveDescription = subjectiveDescription;
    }
}
