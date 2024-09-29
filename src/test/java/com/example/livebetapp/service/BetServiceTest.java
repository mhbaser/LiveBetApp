package com.example.livebetapp.service;

import com.example.livebetapp.model.Bet;
import com.example.livebetapp.repository.BetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BetServiceTest {

    @Mock
    private BetRepository betRepository;

    @InjectMocks
    private BetService betService;

    @Value("${bet.timeout}")
    private long betTimeout = 2000;  // Varsayılan bir değer

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateAndPlaceBet_Success() throws Exception {
        // Arrange
        Bet bet = new Bet();
        bet.setMatchId(1L);
        bet.setStake(100);

        when(betRepository.countByMatchId(bet.getMatchId())).thenReturn(400L);  // Simüle edilen bet sayısı

        // Act
        betService.validateAndPlaceBet(bet);

        // Assert
        verify(betRepository, times(1)).countByMatchId(bet.getMatchId());
        verify(betRepository, times(1)).save(bet);
    }

    @Test
    void testValidateAndPlaceBet_MaximumBetsReached() throws Exception {
        // Arrange
        Bet bet = new Bet();
        bet.setMatchId(1L);
        bet.setStake(100);

        when(betRepository.countByMatchId(bet.getMatchId())).thenReturn(500L);  // Simüle edilen maksimum bet sayısı

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            betService.validateAndPlaceBet(bet);
        });

        assertEquals("Maximum 500 bets reached for this match", exception.getMessage());
        verify(betRepository, times(1)).countByMatchId(bet.getMatchId());
        verify(betRepository, times(0)).save(bet);  // Bet kaydedilmemeli
    }
}
