package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import com.tw.apistackbase.model.Procuratorate;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcuratorateRepositoryTest {
    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Before
    public void setUp() throws Exception{
        List<Procuratorate> procuratorates = new ArrayList<>();
        procuratorates.add(new Procuratorate("z"));
        procuratorates.add(new Procuratorate("c"));
        procuratorates.add(new Procuratorate("b"));
        procuratorates.add(new Procuratorate("a"));
        procuratorateRepository.saveAll(procuratorates);
    }

    @Test
    public void should_return_a_case_when_call_create_a_case() {
        Procuratorate procuratorate = new Procuratorate("ooo");
        Procuratorate createdProcuratorate = procuratorateRepository.save(procuratorate);
        Assert.assertEquals(procuratorate, createdProcuratorate);
    }

    @Test
    public void should_return_null_when_call_create_an_null_case() {
        Procuratorate criminalCase = new Procuratorate();

        Assertions.assertThrows(Exception.class, () ->{
            procuratorateRepository.saveAndFlush(criminalCase);
        });
    }

//    @Test
//    public void should_return_procuratorates_when_find_all_procuratorates() {
//        List<Procuratorate> procuratorates = new ArrayList<>();
//        procuratorates.add(new Procuratorate("z"));
//        procuratorates.add(new Procuratorate("c"));
//        procuratorates.add(new Procuratorate("b"));
//        procuratorates.add(new Procuratorate("a"));
//
//        List<Procuratorate> findedProcuratorates = procuratorateRepository.findAll();
//
//        assertEquals(procuratorates.size(), findedProcuratorates.size());
//    }


}