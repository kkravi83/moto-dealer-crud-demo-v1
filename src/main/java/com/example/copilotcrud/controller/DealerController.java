package com.example.copilotcrud.controller;

import com.example.copilotcrud.dto.DealerRequest;
import com.example.copilotcrud.entity.Dealer;
import com.example.copilotcrud.service.DealerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
@Validated
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping
    public List<Dealer> getAllDealers() {
        return dealerService.getAllDealers();
    }

    @GetMapping("/{id}")
    public Dealer getDealerById(@PathVariable Long id) {
        return dealerService.getDealerById(id);
    }

    @GetMapping("/city/{city}")
    public List<Dealer> getDealersByCity(
            @PathVariable @NotBlank(message = "City must not be blank") String city) {
        return dealerService.getDealersByCity(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dealer createDealer(@Valid @RequestBody DealerRequest request) {
        return dealerService.createDealer(request);
    }

    @PutMapping("/{id}")
    public Dealer updateDealer(@PathVariable Long id, @Valid @RequestBody DealerRequest request) {
        return dealerService.updateDealer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDealer(@PathVariable Long id) {
        dealerService.deleteDealer(id);
    }
}
