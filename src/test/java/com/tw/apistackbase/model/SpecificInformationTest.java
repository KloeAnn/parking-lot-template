package com.tw.apistackbase.model;

import com.tw.apistackbase.repository.CriminalCaseRepository;
import com.tw.apistackbase.repository.SpecificInformationRepository;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpecificInformationTest {
    @Autowired
    private SpecificInformationRepository specificInformationRepository;

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() throws Exception{
        List<SpecificInformation> specificInformations = new ArrayList<>();
        specificInformations.add(new SpecificInformation("zzzzz", "xxxxx"));
        specificInformations.add(new SpecificInformation("ccccc", "vvvvv"));
        specificInformations.add(new SpecificInformation("bbbbb", "nnnnn"));
        specificInformations.add(new SpecificInformation("aaaaaa", "ssssss"));
        specificInformationRepository.saveAll(specificInformations);

        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("ccc", 1563161000, specificInformations.get(0)));
        criminalCases.add(new CriminalCase("vvv", 1563360000, specificInformations.get(1)));
        criminalCases.add(new CriminalCase("bbb", 1563263512, specificInformations.get(2)));
        criminalCases.add(new CriminalCase("bbb", 1563263516, specificInformations.get(3)));
        criminalCaseRepository.saveAll(criminalCases);
    }

    @Test
    public void should_return_a_case_when_call_create_a_case() {
        SpecificInformation specificInformation = new SpecificInformation("a", "b");
        SpecificInformation createdSpecificInfo = specificInformationRepository.save(specificInformation);
        Assert.assertEquals(specificInformation, createdSpecificInfo);
    }

    @Test
    public void should_return_null_when_call_create_an_null_case() {
        SpecificInformation specificInformation = new SpecificInformation();
        Assertions.assertThrows(Exception.class, () ->{
            specificInformationRepository.saveAndFlush(specificInformation);
        });
    }

    @Test
    public void should_return_cases_when_call_find_case_by_id() {
        Optional<SpecificInformation> specificInformation = specificInformationRepository.findById((long)2);
        assertNotNull(specificInformation.get());
    }

    @Test
    public void should_return_case_with_specific_info_when_call_find_case() {
        SpecificInformation specificInformation = new SpecificInformation("a", "b");
        CriminalCase criminalCase = new CriminalCase("bbb", 1563263512, specificInformation);
        CriminalCase createdCriminalCase = criminalCaseRepository.saveAndFlush(criminalCase);
        assertEquals(criminalCase.getSpecificInformation(), createdCriminalCase.getSpecificInformation());
    }

    @Test
    public void should_return_cases_with_specific_info_when_call_find_cases() {
        List<SpecificInformation> specificInformations = new ArrayList<>();
        specificInformations.add(new SpecificInformation("zzzzz", "xxxxx"));
        specificInformations.add(new SpecificInformation("ccccc", "vvvvv"));
        specificInformations.add(new SpecificInformation("bbbbb", "nnnnn"));
        specificInformations.add(new SpecificInformation("aaaaaa", "ssssss"));

        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("ccc", 1563161000, specificInformations.get(0)));
        criminalCases.add(new CriminalCase("vvv", 1563360000, specificInformations.get(1)));
        criminalCases.add(new CriminalCase("bbb", 1563263512, specificInformations.get(2)));
        criminalCases.add(new CriminalCase("bbb", 1563263516, specificInformations.get(3)));

        List<CriminalCase> findCriminalCases = criminalCaseRepository.findAll();

        List<String> specificInfoIds = criminalCases.stream().map(x-> x.getSpecificInformation().getObjectiveDescription()).collect(Collectors.toList());
        List<String> findSpecificInfoIds = findCriminalCases.stream().map(x-> x.getSpecificInformation().getObjectiveDescription()).collect(Collectors.toList());

        assertEquals(specificInfoIds, findSpecificInfoIds);
    }
}