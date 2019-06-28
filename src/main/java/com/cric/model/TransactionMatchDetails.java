package com.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "user_transaction_match_details_record")
@ApiModel
public class TransactionMatchDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_match_id")
	private Integer transactionMatchId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "win_or_lose_status")
	private String win0rLoseStatus;

	@Column(name = "betting_plan_id")
	private Integer planId;

	@Column(name = "team_name")
	private String teamName;

	public Integer getTransactionMatchId() {
		return transactionMatchId;
	}

	public void setTransactionMatchId(Integer transactionMatchId) {
		this.transactionMatchId = transactionMatchId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWin0rLoseStatus() {
		return win0rLoseStatus;
	}

	public void setWin0rLoseStatus(String win0rLoseStatus) {
		this.win0rLoseStatus = win0rLoseStatus;
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
