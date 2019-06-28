package com.cric.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "user_payment_info")
@ApiModel
public class UserPaymentInfo {

	@Id
	@Column(name = "user_payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userPaymentId;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	@Column(name = "amount_received")
	private double amountReceived;

	@Temporal(TemporalType.DATE)
	@Column(name = "received_on")
	private Date receivedOn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "betting_plan_id", referencedColumnName = "betting_plan_id")
	private Plan plan;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public String getModeOfPayment() {
		return modeOfPayment; 
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public Date getReceivedOn() {
		return receivedOn;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public void setReceivedOn(Date receivedOn) {
		this.receivedOn = receivedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getUserPaymentId() {
		return userPaymentId;
	}

	public void setUserPaymentId(Integer userPaymentId) {
		this.userPaymentId = userPaymentId;
	}
	
}
