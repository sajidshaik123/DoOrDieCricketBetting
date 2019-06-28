package com.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "user_current_match_details_record")
@ApiModel
public class CurrentMatch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "current_match_id")
	private Integer currentMatchId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "win_or_lose_status")
	private String winOrLoseStatus;

	@Column(name = "betting_plan_id")
	private Integer planId;

	@Column(name = "team_name")
	private String teamName;

	public Integer getCurrentMatchId() {
		return currentMatchId;
	}

	public void setCurrentMatchId(Integer currentMatchId) {
		this.currentMatchId = currentMatchId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWinOrLoseStatus() {
		return winOrLoseStatus;
	}

	public void setWinOrLoseStatus(String winOrLoseStatus) {
		this.winOrLoseStatus = winOrLoseStatus;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
