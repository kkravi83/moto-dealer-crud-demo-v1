package com.example.copilotcrud.service;

import com.example.copilotcrud.entity.Dealer;
import com.example.copilotcrud.exception.BadRequestException;
import com.example.copilotcrud.repository.DealerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DealerServiceTest {

    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerService dealerService;

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(1L, "Trivandrum Moto Hub", "TMH001", "Thiruvananthapuram", "Kerala",
                "+91 9876543210", 8, LocalDate.of(2022, 6, 15));
    }

    @Test
    void shouldReturnDealersForValidCity() {
        when(dealerRepository.findByCity("Thiruvananthapuram")).thenReturn(List.of(dealer));

        List<Dealer> result = dealerService.getDealersByCity("Thiruvananthapuram");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCity()).isEqualTo("Thiruvananthapuram");
    }

    @Test
    void shouldReturnEmptyListWhenNoDealersFoundForCity() {
        when(dealerRepository.findByCity("UnknownCity")).thenReturn(List.of());

        List<Dealer> result = dealerService.getDealersByCity("UnknownCity");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldThrowBadRequestExceptionWhenCityIsBlank() {
        assertThatThrownBy(() -> dealerService.getDealersByCity("   "))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("City parameter must not be blank");
    }

    @Test
    void shouldThrowBadRequestExceptionWhenCityIsNull() {
        assertThatThrownBy(() -> dealerService.getDealersByCity(null))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("City parameter must not be blank");
    }
}
