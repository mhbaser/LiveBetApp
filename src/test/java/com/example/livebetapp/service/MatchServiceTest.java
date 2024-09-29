package com.example.livebetapp.service;

import com.example.livebetapp.model.Match;
import com.example.livebetapp.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock'ları başlatma
    }

    @Test
    void testAddMatch_Success() {
        // Arrange
        Match match = new Match(); // Test için bir Match nesnesi oluşturuyoruz
        match.setHomeTeam("Team A");
        match.setAwayTeam("Team B");

        Match savedMatch = new Match();
        savedMatch.setId(1L);
        savedMatch.setHomeTeam("Team A");
        savedMatch.setAwayTeam("Team B");

        when(matchRepository.save(match)).thenReturn(savedMatch); // MatchRepository'nin nasıl davranacağını simüle ediyoruz

        // Act
        Match result = matchService.addMatch(match);

        // Assert
        assertEquals(savedMatch, result);
        verify(matchRepository, times(1)).save(match); // `save` metodunun bir kez çağrıldığını doğruluyoruz
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

        List<Match> matchList = Arrays.asList(match1, match2); // Dummy bir liste oluşturuyoruz
        when(matchRepository.findAll()).thenReturn(matchList); // MatchRepository'nin `findAll` metodunun davranışını simüle ediyoruz

        // Act
        List<Match> result = matchService.getAllMatches();

        // Assert
        assertEquals(matchList, result);
        verify(matchRepository, times(1)).findAll(); // `findAll` metodunun bir kez çağrıldığını doğruluyoruz
    }
}
