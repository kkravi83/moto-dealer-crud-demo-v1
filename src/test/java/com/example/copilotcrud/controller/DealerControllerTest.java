package com.example.copilotcrud.controller;

import com.example.copilotcrud.dto.DealerRequest;
import com.example.copilotcrud.entity.Dealer;
import com.example.copilotcrud.service.DealerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import com.example.copilotcrud.exception.BadRequestException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealerController.class)
class DealerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DealerService dealerService;

    @Test
    void shouldGetAllDealers() throws Exception {
        Dealer dealer = new Dealer(1L, "Trivandrum Moto Hub", "TMH001", "Thiruvananthapuram", "Kerala",
                "+91 9876543210", 8, LocalDate.of(2022, 6, 15));

        when(dealerService.getAllDealers()).thenReturn(List.of(dealer));

        mockMvc.perform(get("/api/dealers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dealerName").value("Trivandrum Moto Hub"));
    }

    @Test
    void shouldCreateDealer() throws Exception {
        DealerRequest request = new DealerRequest();
        request.setDealerName("Cochin Wheels Point");
        request.setDealerCode("CWP102");
        request.setCity("Kochi");
        request.setState("Kerala");
        request.setContactNumber("+91 9988776655");
        request.setServiceBayCount(12);
        request.setOnboardingDate(LocalDate.of(2023, 3, 20));

        Dealer saved = new Dealer(3L, request.getDealerName(), request.getDealerCode(), request.getCity(),
                request.getState(), request.getContactNumber(), request.getServiceBayCount(), request.getOnboardingDate());

        when(dealerService.createDealer(any(DealerRequest.class))).thenReturn(saved);

        mockMvc.perform(post("/api/dealers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.dealerCode").value("CWP102"));
    }

    @Test
    void shouldReturnDealersByCity() throws Exception {
        Dealer dealer = new Dealer(1L, "Trivandrum Moto Hub", "TMH001", "Thiruvananthapuram", "Kerala",
                "+91 9876543210", 8, LocalDate.of(2022, 6, 15));

        when(dealerService.getDealersByCity(eq("Thiruvananthapuram"))).thenReturn(List.of(dealer));

        mockMvc.perform(get("/api/dealers/city/Thiruvananthapuram"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Thiruvananthapuram"))
                .andExpect(jsonPath("$[0].dealerName").value("Trivandrum Moto Hub"));
    }

    @Test
    void shouldReturnEmptyListWhenNoDealersInCity() throws Exception {
        when(dealerService.getDealersByCity(eq("UnknownCity"))).thenReturn(List.of());

        mockMvc.perform(get("/api/dealers/city/UnknownCity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnBadRequestWhenServiceThrowsBadRequestException() throws Exception {
        when(dealerService.getDealersByCity(eq("InvalidCity")))
                .thenThrow(new BadRequestException("City parameter must not be blank"));

        mockMvc.perform(get("/api/dealers/city/InvalidCity"))
                .andExpect(status().isBadRequest());
    }
}
