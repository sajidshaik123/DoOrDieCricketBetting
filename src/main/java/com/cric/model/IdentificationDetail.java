package com.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "identification_record")
@ApiModel
public class IdentificationDetail  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identification_record_id")
	private Integer identificationDetailId;

	@Column(name = "aadhar_uid_number")
	private String aadharUidNumber;

	@Column(name = "driving_license_number")
	private String drivingLicenseNumber;

	@Column(name = "passport_number")
	private String passportNumber;

	@Column(name = "user_id")
	private Integer userId;

	public Integer getIdentificationDetailId() {
		return identificationDetailId;
	}

	public void setIdentificationDetailId(Integer identificationDetailId) {
		this.identificationDetailId = identificationDetailId;
	}

	public String getAadharUidNumber() {
		return aadharUidNumber;
	}

	public void setAadharUidNumber(String aadharUidNumber) {
		this.aadharUidNumber = aadharUidNumber;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
