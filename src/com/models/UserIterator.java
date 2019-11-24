package com.models;

public class UserIterator implements Iterator{
    private UserList userList;
	private int index;
	UserIterator(UserList userList){
    	this.userList = userList;
    	this.index = 0;
    }
	public boolean hasNext() {
		if (index < userList.size()) {
			return true;
		}
		return false;
	}
    
    public Object next() {
    	User user = userList.get(index);
    	index++;
    	return user;
    }
}
