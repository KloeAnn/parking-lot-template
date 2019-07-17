package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import com.tw.apistackbase.model.Procuratorate;
import com.tw.apistackbase.model.Prosecutor;
import com.tw.apistackbase.model.SpecificInformation;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcuratorateRepositoryTest {
    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() throws Exception{
        List<Procuratorate> procuratorates = new ArrayList<>();
        procuratorates.add(new Procuratorate("z", null));
        procuratorates.add(new Procuratorate("c", null));
        procuratorates.add(new Procuratorate("b", null));
        procuratorates.add(new Procuratorate("a", null));
        procuratorateRepository.saveAll(procuratorates);

        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("ccc", 1563161000, null, procuratorates.get(0)));
        criminalCases.add(new CriminalCase("vvv", 1563360000, null, procuratorates.get(1)));
        criminalCases.add(new CriminalCase("bbb", 1563263512, null, procuratorates.get(2)));
        criminalCases.add(new CriminalCase("bbb", 1563263516, null, procuratorates.get(3)));
        criminalCaseRepository.saveAll(criminalCases);
    }

    @Test
    public void should_return_a_procuratorate_when_call_create_a_procuratorate() {
        Procuratorate procuratorate = new Procuratorate("ooo", null);
        Procuratorate createdProcuratorate = procuratorateRepository.save(procuratorate);
        Assert.assertEquals(procuratorate, createdProcuratorate);
    }

    @Test
    public void should_return_null_when_call_create_an_null_procuratorate() {
        Procuratorate criminalCase = new Procuratorate();

        Assertions.assertThrows(Exception.class, () ->{
            procuratorateRepository.saveAndFlush(criminalCase);
        });
    }

    @Test
    public void should_return_procuratorates_when_find_all_procuratorates() {
        List<Procuratorate> procuratorates = new ArrayList<>();
        procuratorates.add(new Procuratorate("z", null));
        procuratorates.add(new Procuratorate("c", null));
        procuratorates.add(new Procuratorate("b", null));
        procuratorates.add(new Procuratorate("a", null));

        List<Procuratorate> findedProcuratorates = procuratorateRepository.findAll();

        assertEquals(procuratorates.size(), findedProcuratorates.size());
    }

    @Test
    public void should_return_null_when_call_create_a_case_with_null_procuratorate() {
        CriminalCase criminalCase =  new CriminalCase("ccc", 1563161000, null, null);

        Assertions.assertThrows(Exception.class, () ->{
            criminalCaseRepository.saveAndFlush(criminalCase);
        });
    }

    @Test
    public void should_return_procuratorates_with_new_procuratorate_when_call_find_procuratorates() {
        Procuratorate firstProcuratorate = new Procuratorate("z", null);
        Procuratorate secondProcuratorate = new Procuratorate("c", null);
        Procuratorate thirdProcuratorate = new Procuratorate("b", null);
        Procuratorate fourthProcuratorate = new Procuratorate("a", null);


        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("ccc", 1563161000, null, firstProcuratorate));
        criminalCases.add(new CriminalCase("vvv", 1563360000, null, secondProcuratorate));
        criminalCases.add(new CriminalCase("bbb", 1563263512, null, thirdProcuratorate));
        criminalCases.add(new CriminalCase("bbb", 1563263516, null, fourthProcuratorate));

        List<CriminalCase> findCriminalCases = criminalCaseRepository.findAll();

        List<String> procuratorateNames = criminalCases.stream().map(x-> x.getProcuratorate().getName()).collect(Collectors.toList());
        List<String> findProcuratorateNames = findCriminalCases.stream().map(x-> x.getProcuratorate().getName()).collect(Collectors.toList());

        assertEquals(procuratorateNames, findProcuratorateNames);
    }
}