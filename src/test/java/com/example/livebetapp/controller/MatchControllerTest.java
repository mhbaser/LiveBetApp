package com.example.livebetapp.controller;


import com.example.livebetapp.model.Match;
import com.example.livebetapp.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MatchControllerTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchController matchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMatch_Success() {
        // Arrange
        Match match = new Match(); // Test amaçlı dummy bir Match nesnesi
        match.setHomeTeam("Team A");
        match.setAwayTeam("Team B");

        Match savedMatch = new Match(); // Mock için döndürülen Match nesnesi
        savedMatch.setId(1L);
        savedMatch.setHomeTeam("Team A");
        savedMatch.setAwayTeam("Team B");

        when(matchRepository.save(match)).thenReturn(savedMatch);

        // Act
        ResponseEntity<Match> response = matchController.addMatch(match);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedMatch, response.getBody());
        verify(matchRepository, times(1)).save(match);
    }

    @Test
    void testGetAllMatches_Success() {
        // Arrange
        Match match1 = new Match();
        match1.setId(1L);
        match1.setHomeTeam("Team A");
        match1.setAwayTeam("Team B");

        Match match2 = new Match();
        match2.setId(2L);
        match2.setHomeTeam("Team C");
        match2.setAwayTeam("Team D");

        List<Match> matchList = Arrays.asList(match1, match2);
        when(matchRepository.findAll()).thenReturn(matchList);

        // Act
        ResponseEntity<List<Match>> response = matchController.getAllMatches();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(matchList, response.getBody());
        verify(matchRepository, times(1)).findAll();
    }
}
