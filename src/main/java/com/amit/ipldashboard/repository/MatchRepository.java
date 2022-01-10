package com.amit.ipldashboard.repository;

import java.util.List;

import com.amit.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.web.PageableDefault;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    default List<Match> findLatestMacthByTeam(String teamName, int count) {

        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));

    }

}
