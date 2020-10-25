package com.globits.sample.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "tbl_InformationReport")
public class InformationReport implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue	
	@Column(name="leadId")
	private Long leadId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "agencyId")
	private String agencyId;
	
	@Column(name = "doId")
	private String doId;
	
	@Column(name = "offer_id")
	private String offer_id;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "terms")
	private String terms;
	
	@Column(name = "lead_revenue")
	private String lead_revenue;
	
	@Column(name = "lead_status")
	private String lead_status;
	
	@Column(name = "product_id")
	private String product_id;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "create_date")
	private String create_date;
	
	@Column(name = "lead_phone")
	private String lead_phone;
	
	@Column(name = "click_id")
	private String click_id;
	
	@Column(name = "org_id")
	private String org_id;
	
	@Column(name = "status_Od")
	private String status_Od;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "affiliate_id")
	private String affiliate_id;
	
	public InformationReport() {
		// TODO Auto-generated constructor stub
	}

	

	public Long getLeadId() {
		return leadId;
	}



	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getDoId() {
		return doId;
	}

	public void setDoId(String doId) {
		this.doId = doId;
	}

	public String getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getLead_revenue() {
		return lead_revenue;
	}

	public void setLead_revenue(String lead_revenue) {
		this.lead_revenue = lead_revenue;
	}

	public String getLead_status() {
		return lead_status;
	}

	public void setLead_status(String lead_status) {
		this.lead_status = lead_status;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getLead_phone() {
		return lead_phone;
	}

	public void setLead_phone(String lead_phone) {
		this.lead_phone = lead_phone;
	}

	public String getClick_id() {
		return click_id;
	}

	public void setClick_id(String click_id) {
		this.click_id = click_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getStatus_Od() {
		return status_Od;
	}

	public void setStatus_Od(String status_Od) {
		this.status_Od = status_Od;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAffiliate_id() {
		return affiliate_id;
	}

	public void setAffiliate_id(String affiliate_id) {
		this.affiliate_id = affiliate_id;
	}

	 
}
