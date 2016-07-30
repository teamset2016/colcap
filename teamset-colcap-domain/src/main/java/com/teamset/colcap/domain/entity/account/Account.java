package com.teamset.colcap.domain.entity.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = -6864507679426540932L;

	@Id
	@Column(name = "ACCT_ID")
	private Long acctId;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Column(name = "PASSWORD_HASH")
	private String passwordHash;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BASE_CURR_CODE")
	private String baseCurrCode;

	@Column(name = "SWIFT_CODE")
	private String swiftCode;

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseCurrCode() {
		return baseCurrCode;
	}

	public void setBaseCurrCode(String baseCurrCode) {
		this.baseCurrCode = baseCurrCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctId == null) ? 0 : acctId.hashCode());
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
		Account other = (Account) obj;
		if (acctId == null) {
			if (other.acctId != null)
				return false;
		} else if (!acctId.equals(other.acctId))
			return false;
		return true;
	}

}
