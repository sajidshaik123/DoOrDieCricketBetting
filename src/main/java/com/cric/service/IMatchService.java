package com.cric.service;

import com.cric.model.CurrentMatch;
import com.cric.model.ListResponse;
import com.cric.model.Response;

public interface IMatchService {

	Response saveCurrentMatch(CurrentMatch currentMatch);

	ListResponse getUserMatchList(Integer userId);

	Response getUserMatchByParams(Integer currentMatchId);

	Response updateStatus(String updateStatus, String draw);

	ListResponse getTeams(String search);

}
