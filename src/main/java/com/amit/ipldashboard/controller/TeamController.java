package com.amit.ipldashboard.controller;

import java.time.LocalDate;

import java.util.List;

import com.amit.ipldashboard.model.Match;
import com.amit.ipldashboard.model.Team;
import com.amit.ipldashboard.repository.MatchRepository;
import com.amit.ipldashboard.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
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

    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();

    }

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {

        Team team = this.teamRepository.findByTeamName(teamName);

        // Pageable pageable = PageRequest.of(0, 4);

        team.setMatches(this.matchRepository.findLatestMacthByTeam(teamName, 4));

        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
                teamName,
                startDate,
                endDate);
    }

}
