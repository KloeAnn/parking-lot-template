package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CriminalCaseRepositoryTest {
    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() throws Exception{
        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("ccc", 1563161000));
        criminalCases.add(new CriminalCase("vvv", 1563360000));
        criminalCases.add(new CriminalCase("bbb", 1563263512));
        criminalCaseRepository.saveAll(criminalCases);
    }

    @Test
    public void should_return_a_case_when_call_create_a_case() {
        CriminalCase criminalCase = new CriminalCase("a", 1563361068);
        CriminalCase createdCriminalCase = criminalCaseRepository.save(criminalCase);
        Assert.assertEquals(criminalCase, createdCriminalCase);
    }

    @Test
    public void should_return_null_when_call_create_an_null_case() {
        CriminalCase criminalCase = new CriminalCase();

        Assertions.assertThrows(Exception.class, () ->{
            criminalCaseRepository.saveAndFlush(criminalCase);
        });
    }

    @Test
    public void should_return_cases_when_call_find_case_by_id() {
       Optional<CriminalCase> criminalCase = criminalCaseRepository.findById((long)1);
       assertNotNull(criminalCase.get());
    }


}