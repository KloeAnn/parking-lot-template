package com.tw.apistackbase.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private int capacity;

    private String address;

    public ParkingLot(){}

    public ParkingLot(String name,int capacity,String address){
        this.name=name;
        this.capacity=capacity;
        this.address=address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
