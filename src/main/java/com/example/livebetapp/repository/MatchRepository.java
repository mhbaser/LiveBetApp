package com.example.livebetapp.repository;

import com.example.livebetapp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}