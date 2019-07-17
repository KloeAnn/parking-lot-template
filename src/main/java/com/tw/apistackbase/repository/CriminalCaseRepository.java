package com.tw.apistackbase.repository;


import com.tw.apistackbase.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {

}
