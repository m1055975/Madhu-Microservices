package com.mindtree.consumer.dto;

public class MessageDto {
	
    private String userId;
	
	private String userName;
	
	private int orderId;
	
	private String restaurantName;
	
	private double totalAmount;
	
	private String message;

	public MessageDto() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "MessageDto [userId=" + userId + ", userName=" + userName + ", orderId=" + orderId + ", restaurantName="
				+ restaurantName + ", totalAmount=" + totalAmount + ", message=" + message + "]";
	}

	
}



