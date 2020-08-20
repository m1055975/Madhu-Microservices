package com.mindtree.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PaymentDto {
	
	@JsonIgnore
    private int paymentId; 
	
	private String debitCardNo;
	
	private String nameOnCard;
	
	private String expiryMonth;
	
	private String expiryYear;
	
	private String cvv;
	
	private double totalAmount;
	
	@JsonIgnore
	private boolean paymentStatus;
	
	public PaymentDto() {
		super();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getDebitCardNo() {
		return debitCardNo;
	}

	public void setDebitCardNo(String debitCardNo) {
		this.debitCardNo = debitCardNo;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = false;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	
	
	

}
