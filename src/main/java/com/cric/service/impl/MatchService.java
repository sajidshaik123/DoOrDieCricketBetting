package com.cric.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.cric.common.CommonConstants;
import com.cric.model.CurrentMatch;
import com.cric.model.ListResponse;
import com.cric.model.Response;
import com.cric.repository.IMatchRepository;
import com.cric.service.IMatchService;



@Service
public class MatchService implements IMatchService {

	@Autowired
	IMatchRepository matchRepository;

	@Override
	public Response saveCurrentMatch(CurrentMatch currentMatch) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		CurrentMatch currentMatchObj = null;
		try {
			currentMatchObj = matchRepository.save(currentMatch);
			response.setData(currentMatchObj);
			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			status.setMessage(CommonConstants.Match.SAVE);
			response.setStatus(status);

		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public ListResponse getUserMatchList(Integer userId) {

		ListResponse response = new ListResponse();
		ListResponse.Status status = new ListResponse.Status();
		List<CurrentMatch> currentMatchList = null;
		if (userId != null) {
			currentMatchList = matchRepository.findByUserId(userId);
		} else {
			currentMatchList = (List<CurrentMatch>) matchRepository.findAll();
		}
		status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		status.setMessage(CommonConstants.Match.LISTFOUND);
		response.setData(currentMatchList);
		response.setStatus(status);

		return response;
	}

	@Override
	public Response getUserMatchByParams(Integer currentMatchId) {

		Response response = new Response();
		Response.Status status = new Response.Status();
		CurrentMatch currentMatchObj = null;
		if (currentMatchId != null) {
			currentMatchObj = matchRepository.findByCurrentMatchId(currentMatchId);

			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			status.setMessage(CommonConstants.Match.RECORDFOUND);
			response.setStatus(status);
			response.setData(currentMatchObj);

		} else {
			response.setData(null);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
			status.setMessage(CommonConstants.Match.RECORDFOUND);
			response.setStatus(status);

		}
		status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		status.setMessage(CommonConstants.Match.LISTFOUND);
		response.setData(currentMatchObj);
		response.setStatus(status);

		return response;
	}

	@Override
	public Response updateStatus(String updateStatus, String draw) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		List<CurrentMatch> currentMatchList = new ArrayList<>();
		List<CurrentMatch> currentMatchListDb = null;
			currentMatchListDb = (List<CurrentMatch>) matchRepository.findAll();
		if (updateStatus != null) {
			for (CurrentMatch currentMatchitr : currentMatchListDb) {
				CurrentMatch currentMatchWon = new CurrentMatch();
				if (currentMatchitr.getTeamName().equals(updateStatus)) {
					currentMatchWon = currentMatchitr;
					currentMatchWon.setWinOrLoseStatus(updateStatus+CommonConstants.Match.WON);
					currentMatchList.add(currentMatchWon);
				}else {
					currentMatchWon = currentMatchitr;
					currentMatchWon.setWinOrLoseStatus(currentMatchitr.getTeamName() +CommonConstants.Match.LOST);
					currentMatchList.add(currentMatchWon);
				}
			}
		}
		if (draw != null) {
			for (CurrentMatch currentMatchitr : currentMatchListDb) {
				CurrentMatch currentMatchDraw=new CurrentMatch();
				currentMatchDraw = currentMatchitr;
				currentMatchDraw.setWinOrLoseStatus(draw);
				currentMatchList.add(currentMatchDraw);
			}
		}
		if (!currentMatchList.isEmpty()) {
			matchRepository.saveAll(currentMatchList);
			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			status.setMessage(CommonConstants.Match.LISTFOUND);
			response.setData(currentMatchList);
			response.setStatus(status);
		}
		return response;
	}

	@Override
	public ListResponse getTeams(String search) {
		ListResponse response = new ListResponse();
		ListResponse.Status status = new ListResponse.Status();
		List<CurrentMatch> currentMatchList = null;
		if (!search.isEmpty()) {
			currentMatchList = matchRepository.findByTeamNameLike(search);
		} else {
			currentMatchList = (List<CurrentMatch>) matchRepository.findAll();
		}
		status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		status.setMessage(CommonConstants.Match.LISTFOUND);
		response.setData(currentMatchList);
		response.setStatus(status);
		return response;
	}

}
