package com.tw.apistackbase.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Long.valueOf;

@RestController
public class PakingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostMapping ("/parking-lots")
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot)throws Exception{
        ParkingLot newParingLot=parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(newParingLot);
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity deleteParkingLot(@RequestParam Long id){
        parkingLotRepository.deleteById(id.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/parking-lots")
    public ResponseEntity getParkingLots(@RequestParam(value = "page", defaultValue = "1") long page,
                                         @RequestParam(value = "pageSize", defaultValue = "0") long pageSize) {
        return pageSize == 0 ? ResponseEntity.ok(parkingLotRepository.findAll())
                : ResponseEntity.ok(parkingLotRepository.findAll().stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList()));
    }
    @GetMapping("/parking-lots/{id}")
    public ResponseEntity findParkingLotByID(@PathVariable long id) {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
        if (optionalParkingLot.isPresent()) return ResponseEntity.ok(optionalParkingLot.get());
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/parking-lots/{id}")
    public ResponseEntity updateParkingLotSetCapacityByID(@PathVariable long id, @RequestBody ParkingLot parkingLot) {

        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
        if (optionalParkingLot.isPresent()) {
            ParkingLot newParkingLot = optionalParkingLot.get();
            newParkingLot.setCapacity(parkingLot.getCapacity());
            newParkingLot = parkingLotRepository.save(newParkingLot);
            return ResponseEntity.ok(newParkingLot);
        }
        else return ResponseEntity.notFound().build();
    }

}
