package com.example.copilotcrud.service;

import com.example.copilotcrud.dto.DealerRequest;
import com.example.copilotcrud.entity.Dealer;
import com.example.copilotcrud.exception.BadRequestException;
import com.example.copilotcrud.exception.ResourceNotFoundException;
import com.example.copilotcrud.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    public Dealer getDealerById(Long id) {
        return dealerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id: " + id));
    }

    public Dealer createDealer(DealerRequest request) {
        if (dealerRepository.existsByDealerCode(request.getDealerCode())) {
            throw new BadRequestException("Dealer code already exists: " + request.getDealerCode());
        }

        Dealer dealer = mapToEntity(request, new Dealer());
        return dealerRepository.save(dealer);
    }

    public Dealer updateDealer(Long id, DealerRequest request) {
        Dealer existing = getDealerById(id);

        if (dealerRepository.existsByDealerCodeAndIdNot(request.getDealerCode(), id)) {
            throw new BadRequestException("Another dealer already uses code: " + request.getDealerCode());
        }

        mapToEntity(request, existing);
        return dealerRepository.save(existing);
    }

    public void deleteDealer(Long id) {
        Dealer existing = getDealerById(id);
        dealerRepository.delete(existing);
    }

    private Dealer mapToEntity(DealerRequest request, Dealer dealer) {
        dealer.setDealerName(request.getDealerName());
        dealer.setDealerCode(request.getDealerCode());
        dealer.setCity(request.getCity());
        dealer.setState(request.getState());
        dealer.setContactNumber(request.getContactNumber());
        dealer.setServiceBayCount(request.getServiceBayCount());
        dealer.setOnboardingDate(request.getOnboardingDate());
        return dealer;
    }
}
