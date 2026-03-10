package com.example.copilotcrud.controller;

import com.example.copilotcrud.dto.DealerRequest;
import com.example.copilotcrud.entity.Dealer;
import com.example.copilotcrud.service.DealerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
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
