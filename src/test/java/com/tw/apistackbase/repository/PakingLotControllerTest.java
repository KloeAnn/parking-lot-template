package com.tw.apistackbase.repository;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PakingLotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    private List<ParkingLot> parkingLots=new ArrayList<>();
    @Before
    public void befor_each_test(){
        parkingLots=new ArrayList(){{
            add(new ParkingLot("parkingLot1",10,"beijing"));
            add(new ParkingLot("parkingLot2",10,"nanjing"));
            add(new ParkingLot("parkingLot3",10,"dalian"));
            add(new ParkingLot("parkingLot4",10,"suzhou"));
            add(new ParkingLot("parkingLot5",10,"yunnan"));
            add(new ParkingLot("parkingLot6",10,"kunming"));
        }};
    }

    @Test
    public void should_return_parking_lot_when_save_parking_lot()throws Exception{
        // given
        ParkingLot parkingLot = parkingLots.get(0);
        // when
        mockMvc.perform(post("/parking-lots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("parkingLot1"));
    }

    @Test
    public void should_return_parking_lots_page_by_page_when_get_them() throws Exception {
        // given
        // when
        mockMvc.perform(get("/parking-lots?page=1&pageSize=5"))
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    public void should_return_a_parkingLot_when_find_it_by_id() throws Exception {
        // given
        parkingLots.get(0).setId(1L);
        //when
        mockMvc.perform(get("/parking-lots/1"))
                // then
                .andExpect(jsonPath("$.name").value(parkingLots.get(0).getName()));
    }

    @Test
    public void should_return_a_parkingLot_when_update_its_capacity_by_id() throws Exception {
        // given
        parkingLots.get(0).setId(1L);
        parkingLots.get(0).setCapacity(1000);


        // when
        mockMvc.perform(put("/parking-lots/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"capacity\":1000\n" +
                        "}"))
                // then
                .andExpect(jsonPath("$.capacity").value(1000));
    }
}