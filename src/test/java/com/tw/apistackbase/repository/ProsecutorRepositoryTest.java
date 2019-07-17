package com.tw.apistackbase.repository;

import com.sun.org.apache.xerces.internal.xs.StringList;
import com.tw.apistackbase.model.CriminalCase;
import com.tw.apistackbase.model.Procuratorate;
import com.tw.apistackbase.model.Prosecutor;
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
public class ProsecutorRepositoryTest {
    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Before
    public void setUp() throws Exception{
        List<Prosecutor> procuratorates = new ArrayList<>();
        procuratorates.add(new Prosecutor("zzz"));
        procuratorates.add(new Prosecutor("ccc"));
        procuratorates.add(new Prosecutor("bbbb"));
        procuratorates.add(new Prosecutor("aaaa"));
        prosecutorRepository.saveAll(procuratorates);
    }

    @Test
    public void should_return_a_procurators_when_call_create_a_procurators() {
        Prosecutor procurator = new Prosecutor("ooo");
        Prosecutor createdProcurato = prosecutorRepository.save(procurator);
        Assert.assertEquals(procurator, createdProcurato);
    }

    @Test
    public void should_return_null_when_call_create_an_null_procurator() {
        Prosecutor procuratorate = new Prosecutor();

        Assertions.assertThrows(Exception.class, () ->{
            prosecutorRepository.saveAndFlush(procuratorate);
        });
    }

    @Test
    public void should_return_procurators_when_find_all_procurators() {
        List<Prosecutor> procurators = new ArrayList<>();
        procurators.add(new Prosecutor("z"));
        procurators.add(new Prosecutor("c"));
        procurators.add(new Prosecutor("b"));
        procurators.add(new Prosecutor("a"));

        List<Prosecutor> findedProcurators = prosecutorRepository.findAll();

        assertEquals(procurators.size(), findedProcurators.size());
    }

    @Test
    public void should_return_procuratorates_when_find_all_procuratorates() {
        List<Prosecutor> procurators = new ArrayList<>();
        procurators.add(new Prosecutor("z"));
        procurators.add(new Prosecutor("c"));
        procurators.add(new Prosecutor("b"));
        procurators.add(new Prosecutor("a"));

        Procuratorate procuratorate = new Procuratorate("z", procurators);
        Procuratorate createdProcuratorate = procuratorateRepository.save(procuratorate);

        List<String> procuratorNames = procurators.stream().map(Prosecutor::getName).collect(Collectors.toList());
        List<String> creadtedProcuratorNames = createdProcuratorate.getProsecutorList().stream().map(Prosecutor::getName).collect(Collectors.toList());

        assertEquals(procuratorNames, creadtedProcuratorNames);
    }

}