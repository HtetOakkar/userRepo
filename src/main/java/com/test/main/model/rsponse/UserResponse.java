package com.test.main.model.rsponse;

import java.util.List;
import java.util.Set;

public class UserResponse {
	private long id;
	private String userName;
	private String password;
	private String mobileNumber;
	private Set<UserRoleResponse> roles;
	private List<UserAddressResponse> addresses;
	private UserAccountResponse account;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<UserRoleResponse> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoleResponse> roles) {
		this.roles = roles;
	}

	public List<UserAddressResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UserAddressResponse> addresses) {
		this.addresses = addresses;
	}

	public UserAccountResponse getAccount() {
		return account;
	}

	public void setAccount(UserAccountResponse account) {
		this.account = account;
	}

}
