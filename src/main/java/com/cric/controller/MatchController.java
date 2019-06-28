package com.cric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cric.model.CurrentMatch;
import com.cric.model.ListResponse;
import com.cric.model.Response;
import com.cric.repository.IMatchRepository;
import com.cric.service.IMatchService;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/match")
public class MatchController {
	
	
	@Autowired
	IMatchService matchService;

	@Autowired
	IMatchRepository matchRepository;
	
	@CacheEvict(value = {"match"}, allEntries = true)
	@PostMapping(value = "/save")
	@ApiOperation(value = "Save CurrentMatch", nickname = "Save CurrentMatch", notes = "Save CurrentMatch", produces = "application/json", consumes = "application/json")
	public Response saveCurrentMatch(@RequestBody CurrentMatch currentMatch) {
		return matchService.saveCurrentMatch(currentMatch);
	}
	
	@Cacheable("userMatches")
	@GetMapping(value = "/matchList")
	@ApiOperation(nickname = "get match all details for Particular User", consumes = "application/json", value = "")
	public ListResponse getUserMatchList(@RequestParam(value = "userId", required = false) Integer userId) {
		return matchService.getUserMatchList(userId);
	}
	
	@Cacheable("match")
	@GetMapping(value = "/params")
	@ApiOperation(value = "", nickname = "get Particular Match", produces = "application/json", consumes = "application/json")
	public Response getUserMatchByParams(@RequestParam(value = "currentMatchId", required = false) Integer currentMatchId) {
		return matchService.getUserMatchByParams(currentMatchId);
	}
	
	@Cacheable("match")
	@GetMapping(value = "/team")
	@ApiOperation(value = "get team all details", nickname = "get all team", notes = "This will get all the taem  details", produces = "application/json", consumes = "application/json")
	public ListResponse getTeam(@RequestParam(value = "search", required = false) String search) {
		return matchService.getTeams(search);
	}
	
	@CacheEvict(value = {"match"}, allEntries = true)
	@PostMapping(value = "/updateMatchStatus")
	@ApiOperation(value = "Update Win Or Lose", nickname = "Update Win Or Lose", notes = "Update Win Or Lose", produces = "application/json", consumes = "application/json")
	public Response updateStatus(@RequestParam(value = "updateStatus", required = false) String updateStatus,@RequestParam(value = "draw", required = false) String draw) {
		return matchService.updateStatus(updateStatus,draw);
	}

}
