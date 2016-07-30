package com.teamset.colcap.domain.entity.collateral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COLLATERAL")
public class Collateral implements Serializable {

	private static final long serialVersionUID = 3040265364788316999L;

	@Id
	@Column(name = "COLL_ID")
	private Long collId;

	@Column(name = "ACCT_ID")
	private Long acctId;

	@Column(name = "COLL_TYPE")
	private String collType;

	@Column(name = "COLL_NAME")
	private String collName;

	@Column(name = "COLL_VAL")
	private BigDecimal collVal;

	@Column(name = "COLL_VAL_CURR_CODE")
	private String collValCurrCode;

	@Column(name = "COLL_AGE")
	private Integer collAge;

	@Column(name = "CREATE_DT")
	private Date createDt;

	@Column(name = "CREATE_BY")
	private String createBy;

	@Column(name = "UPDATE_DT")
	private Date updateDt;

	@Column(name = "UPDATE_BY")
	private String updateBy;

	@Column(name = "CTR_PARTY_AGE")
	private Integer ctrPartyAge;

	@Column(name = "CTR_PARTY_RATING")
	private String ctrPartyRating;

	@Column(name = "EXP_SECURED")
	private BigDecimal expSecured;

	@Column(name = "EXP_CURR_CODE")
	private String expCurrCode;

	@Column(name = "COLL_RATING")
	private String collRating;

	public Long getCollId() {
		return collId;
	}

	public void setCollId(Long collId) {
		this.collId = collId;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public String getCollType() {
		return collType;
	}

	public void setCollType(String collType) {
		this.collType = collType;
	}

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public BigDecimal getCollVal() {
		return collVal;
	}

	public void setCollVal(BigDecimal collVal) {
		this.collVal = collVal;
	}

	public String getCollValCurrCode() {
		return collValCurrCode;
	}

	public void setCollValCurrCode(String collValCurrCode) {
		this.collValCurrCode = collValCurrCode;
	}

	public Integer getCollAge() {
		return collAge;
	}

	public void setCollAge(Integer collAge) {
		this.collAge = collAge;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getCtrPartyAge() {
		return ctrPartyAge;
	}

	public void setCtrPartyAge(Integer ctrPartyAge) {
		this.ctrPartyAge = ctrPartyAge;
	}

	public String getCtrPartyRating() {
		return ctrPartyRating;
	}

	public void setCtrPartyRating(String ctrPartyRating) {
		this.ctrPartyRating = ctrPartyRating;
	}

	public BigDecimal getExpSecured() {
		return expSecured;
	}

	public void setExpSecured(BigDecimal expSecured) {
		this.expSecured = expSecured;
	}

	public String getExpCurrCode() {
		return expCurrCode;
	}

	public void setExpCurrCode(String expCurrCode) {
		this.expCurrCode = expCurrCode;
	}

	public String getCollRating() {
		return collRating;
	}

	public void setCollRating(String collRating) {
		this.collRating = collRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collId == null) ? 0 : collId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collateral other = (Collateral) obj;
		if (collId == null) {
			if (other.collId != null)
				return false;
		} else if (!collId.equals(other.collId))
			return false;
		return true;
	}

}
