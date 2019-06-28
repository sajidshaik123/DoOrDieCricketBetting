package com.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "betting_plan")
@ApiModel
public class Plan extends BaseEntity {

	@Id
	@Column(name = "betting_plan_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "cost")
	private Double cost;

	@Column(name = "tax_1")
	private Double tax1;

	@Column(name = "tax_2")
	private Double tax2;

	@Column(name = "plan_type")
	private String planType;

	@Column(name = "document_file_name")
	private String documentFileName;

	@Column(name = "document_content_type")
	private String documentContentType;

	@Column(name = "document_content")
	@Lob
	private byte[] documentContent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getTax1() {
		return tax1;
	}

	public void setTax1(Double tax1) {
		this.tax1 = tax1;
	}

	public Double getTax2() {
		return tax2;
	}

	public void setTax2(Double tax2) {
		this.tax2 = tax2;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	public String getDocumentContentType() {
		return documentContentType;
	}

	public void setDocumentContentType(String documentContentType) {
		this.documentContentType = documentContentType;
	}

	public byte[] getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

}
