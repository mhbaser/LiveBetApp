package com.example.livebetapp.service;

import com.example.livebetapp.model.Bet;
import com.example.livebetapp.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class BetService {

    @Value("${bet.timeout}")
    private long betTimeout;
    private final BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public void validateAndPlaceBet(Bet bet) throws Exception {
        long betCount = betRepository.countByMatchId(bet.getMatchId());
        if (betCount >= 500) {
            throw new Exception("Maximum 500 bets reached for this match");
        }
        betRepository.save(bet);
    }
}
