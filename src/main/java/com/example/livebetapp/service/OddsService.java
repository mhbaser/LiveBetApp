package com.example.livebetapp.service;

import com.example.livebetapp.model.Match;
import com.example.livebetapp.repository.MatchRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OddsService {

    private final MatchRepository matchRepository;
    private final Random random = new Random();

    public OddsService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void updateOdds() {
        List<Match> matches = matchRepository.findAll();
        for (Match match : matches) {
            match.setHomeWinOdds(generateRandomOdds());
            match.setDrawOdds(generateRandomOdds());
            match.setAwayWinOdds(generateRandomOdds());
            matchRepository.save(match);
        }
    }

    private double generateRandomOdds() {
        return 1.0 + (10.0 - 1.0) * random.nextDouble();
    }
}
