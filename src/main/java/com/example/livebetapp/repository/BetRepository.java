package com.example.livebetapp.repository;

import com.example.livebetapp.model.Bet;
import com.example.livebetapp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
    long countByMatchId(Long matchId);

}