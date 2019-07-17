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

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        criminalCases.add(new CriminalCase("bbb", 1563263516));
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
       Optional<CriminalCase> criminalCase = criminalCaseRepository.findById((long)2);
       assertNotNull(criminalCase.get());
    }

    @Test
    public void should_return_cases_when_call_find_case_order_by_time() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("vvv", 1563360000));
        criminalCases.add(new CriminalCase("bbb", 1563263516));
        criminalCases.add(new CriminalCase("bbb", 1563263512));
        criminalCases.add(new CriminalCase("ccc", 1563161000));

        List<CriminalCase> findedCriminalCases = criminalCaseRepository.findAllByTimeSort();
        List<Long> findedTimes = findedCriminalCases.stream().mapToLong(CriminalCase::getIncidentTime).boxed().collect(Collectors.toList());
        List<Long> times = criminalCases.stream().mapToLong(CriminalCase::getIncidentTime).boxed().collect(Collectors.toList());
        assertEquals(findedTimes, times);
    }

    @Test
    public void should_return_cases_when_call_find_case_by_name() {
        List<CriminalCase> findedCriminalCases = criminalCaseRepository.findByName("bbb");
        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("bbb", 1563263516));
        criminalCases.add(new CriminalCase("bbb", 1563263512));
        assertEquals(findedCriminalCases.size(), criminalCases.size());
    }

    @Test
    public void should_no_return_when_call_delete_case_by_id() {
        List<CriminalCase> beforeCriminalCases = criminalCaseRepository.findAll();
        criminalCaseRepository.deleteById((long) 1);
        List<CriminalCase> criminalCases = criminalCaseRepository.findAll();
        assertEquals(criminalCases.size(), beforeCriminalCases.size() - 1);
    }
}