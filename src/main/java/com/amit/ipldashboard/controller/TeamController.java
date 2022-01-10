package com.amit.ipldashboard.controller;

import com.amit.ipldashboard.model.Team;
import com.amit.ipldashboard.repository.MatchRepository;
import com.amit.ipldashboard.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    private MatchRepository matchRepository;

    // if not using @Autowired, then you can define constructor for this.
    public TeamController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // check
    @GetMapping("/test")
    public String check() {
        return "working!!!";
    }

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {

        Team team = this.teamRepository.findByTeamName(teamName);

        // Pageable pageable = PageRequest.of(0, 4);

        team.setMatches(this.matchRepository.findLatestMacthByTeam(teamName, 4));

        return team;
    }

}
