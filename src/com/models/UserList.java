package com.models;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.controller.GUI;

public class UserList implements Aggregate{
	private User[] users;
	UserList(Criteria c){
		List d = c.list();
		users = new User[d.size()];
		for (int i = 0; i < d.size(); i++) {
			users[i] = (User) d.get(i);
		}
	}
	public User get(int index) {
        return users[index];
    }
    public int size() {
        return users.length;
    }
    public Iterator iterator() {
        return new UserIterator(this);
    }
}
