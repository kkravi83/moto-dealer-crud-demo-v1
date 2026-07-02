package com.example.copilotcrud.repository;

import com.example.copilotcrud.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    boolean existsByDealerCode(String dealerCode);
    boolean existsByDealerCodeAndIdNot(String dealerCode, Long id);
    List<Dealer> findByCity(String city);
}
