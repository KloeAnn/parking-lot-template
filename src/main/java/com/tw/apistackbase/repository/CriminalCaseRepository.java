package com.tw.apistackbase.repository;


import com.tw.apistackbase.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
    @Query(value = "SELECT c FROM CriminalCase c ORDER BY incidentTime desc")
    List<CriminalCase> findAllByTimeSort();

    List<CriminalCase> findByName(String name);
}
