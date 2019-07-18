package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Prosecutor;
import com.tw.apistackbase.repository.ProsecutorRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProsecutorController {

    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @GetMapping("/prosecutors")
    public ResponseEntity getProsecutors() {
        List<Prosecutor> prosecutors = prosecutorRepository.findAll();
        return ResponseEntity.ok(prosecutors);
    }

}
