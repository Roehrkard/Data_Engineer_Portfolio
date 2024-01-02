package com.roehr.equipmenttracker.controller;

import com.roehr.equipmenttracker.model.Equipment;
import com.roehr.equipmenttracker.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        EquipmentController equipmentController = new EquipmentController(equipmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
    }

    @Test
    void getAllEquipmentTest() throws Exception {
        List<Equipment> equipmentList = Arrays.asList(
                new Equipment("Drill", "Available", 10, "Industrial drill"),
                new Equipment("Hammer", "In Use", 5, "Standard hammer")
        );

        given(equipmentService.getAllEquipment()).willReturn(equipmentList);

        mockMvc.perform(get("/api/equipment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Drill")))
                .andExpect(jsonPath("$[1].name", is("Hammer")));
    }

    @Test
    void updateEquipmentQuantityTest() throws Exception {
        Long equipmentId = 1L;
        int newQuantity = 15;
        Equipment equipment = new Equipment("Drill", "Available", newQuantity, "Industrial drill");

        given(equipmentService.updateEquipmentQuantity(equipmentId, newQuantity)).willReturn(equipment);

        mockMvc.perform(put("/api/equipment/{id}/quantity", equipmentId)
                        .param("quantity", String.valueOf(newQuantity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qty", is(newQuantity)))
                .andExpect(jsonPath("$.name", is("Drill")));

        verify(equipmentService).updateEquipmentQuantity(equipmentId, newQuantity);
    }

    @Test
    void updateEquipmentStatusTest() throws Exception {
        Long equipmentId = 1L;
        String newStatus = "In Maintenance";
        Equipment equipment = new Equipment("Drill", newStatus, 10, "Industrial drill");

        given(equipmentService.updateEquipmentStatus(equipmentId, newStatus)).willReturn(equipment);

        mockMvc.perform(put("/api/equipment/{id}/status", equipmentId)
                        .param("status", newStatus))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(newStatus)))
                .andExpect(jsonPath("$.name", is("Drill")));

        verify(equipmentService).updateEquipmentStatus(equipmentId, newStatus);
    }
}
