package com.splitwise.DTO;

import java.util.Set;

public class ExpenseDTO {

	private Long expenseId;
	
	private String expenseDescription;
	
	private String payerEmail;
	
	private Set<String> addedMembers;
	
	private String groupName;
	
	private Long amount;

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	public Set<String> getAddedMembers() {
		return addedMembers;
	}

	public void setAddedMembers(Set<String> addedMembers) {
		this.addedMembers = addedMembers;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
	
	
}
