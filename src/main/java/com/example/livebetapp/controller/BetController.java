package com.example.livebetapp.controller;

import com.example.livebetapp.model.Bet;
import com.example.livebetapp.service.BetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public ResponseEntity<String> placeBet(@RequestBody Bet bet) {
        try {
            betService.validateAndPlaceBet(bet);
            return ResponseEntity.ok("Bet placed successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
