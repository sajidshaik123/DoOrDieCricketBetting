package com.cric.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public abstract class BaseEntity extends java.lang.Object
implements java.io.Serializable{

	@Column(name = "is_active")
	public Boolean isActive;

	@Column(name = "created_by")
	public Integer createdBy;

	@Column(name = "updated_by")
	public Integer updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	public Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Date updatedOn;

	@ApiModelProperty(example = "true")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ApiModelProperty(example = "1")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@ApiModelProperty(example = "1")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@ApiModelProperty(example = "2019-03-22 00:00:00")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@ApiModelProperty(example = "2019-03-22 00:00:00")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
