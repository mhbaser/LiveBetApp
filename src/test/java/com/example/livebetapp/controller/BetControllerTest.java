package com.example.livebetapp.controller;


import com.example.livebetapp.model.Bet;
import com.example.livebetapp.service.BetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BetControllerTest {

    @Mock
    private BetService betService;

    @InjectMocks
    private BetController betController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceBet_Success() throws Exception {
        // Arrange
        Bet bet = new Bet(); // Dummy bet object, set values as needed
        doNothing().when(betService).validateAndPlaceBet(bet);

        // Act
        ResponseEntity<String> response = betController.placeBet(bet);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bet placed successfully!", response.getBody());
        verify(betService, times(1)).validateAndPlaceBet(bet);
    }

    @Test
    void testPlaceBet_Failure() throws Exception {
        // Arrange
        Bet bet = new Bet(); // Dummy bet object, set values as needed
        doThrow(new RuntimeException("Invalid bet")).when(betService).validateAndPlaceBet(bet);

        // Act
        ResponseEntity<String> response = betController.placeBet(bet);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid bet", response.getBody());
        verify(betService, times(1)).validateAndPlaceBet(bet);
    }
}
