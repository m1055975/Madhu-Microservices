package com.mindtree.userservice.dto;

public class UserDetailsDto {
	
	    private String userId;
		
	    private String address;
		
		private long phoneNumber;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
		

}
