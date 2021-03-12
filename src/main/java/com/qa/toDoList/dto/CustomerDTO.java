package com.qa.toDoList.dto;

public class CustomerDTO  {
	
	private int cid;
	private String username;
	
	public CustomerDTO() {
		super();
		
	}

	public CustomerDTO(int cid, String username) {
		super();
		this.cid = cid;
		this.username = username;
	}

	public int getCid() {
		return cid;
	}

	public void setId(int cid) {
		this.cid = cid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		CustomerDTO other = (CustomerDTO) obj;
		if (cid != other.cid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
