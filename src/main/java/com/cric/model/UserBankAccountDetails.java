package com.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
@Entity
@Table(name = "user_bank_account_details_record")
@ApiModel
public class UserBankAccountDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_account_record_id")
	private Integer bankAccountRecordId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "paytym_number")
	private String paytymNumber;
	
	@Column(name = "phonepee_number")
	private String phonePeeNumber;
	
	@Column(name = "googlepay_num")
	private String googlePayNumber;

	public Integer getBankAccountRecordId() {
		return bankAccountRecordId;
	}

	public void setBankAccountRecordId(Integer bankAccountRecordId) {
		this.bankAccountRecordId = bankAccountRecordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPaytymNumber() {
		return paytymNumber;
	}

	public void setPaytymNumber(String paytymNumber) {
		this.paytymNumber = paytymNumber;
	}

	public String getPhonePeeNumber() {
		return phonePeeNumber;
	}

	public void setPhonePeeNumber(String phonePeeNumber) {
		this.phonePeeNumber = phonePeeNumber;
	}

	public String getGooglePayNumber() {
		return googlePayNumber;
	}

	public void setGooglePayNumber(String googlePayNumber) {
		this.googlePayNumber = googlePayNumber;
	}
	
	
}
