package com.account.service.accountService.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	private int accountFromId;
	private int accountToId;
	private int transferedAmount;
	
	
	
	
	public int getAccountFromId() {
		return accountFromId;
	}
	public void setAccountFromId(int accountFromId) {
		this.accountFromId = accountFromId;
	}
	public int getAccountToId() {
		return accountToId;
	}
	public void setAccountToId(int accountToId) {
		this.accountToId = accountToId;
	}
	public int getTransferedAmount() {
		return transferedAmount;
	}
	public void setTransferedAmount(int transferedAmount) {
		this.transferedAmount = transferedAmount;
	}
	
	
	
	

}
