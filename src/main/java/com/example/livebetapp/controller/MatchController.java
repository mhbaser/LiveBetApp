package com.example.livebetapp.controller;

import com.example.livebetapp.model.Match;
import com.example.livebetapp.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        Match savedMatch = matchRepository.save(match);
        return ResponseEntity.ok(savedMatch);
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return ResponseEntity.ok(matches);
    }
}
