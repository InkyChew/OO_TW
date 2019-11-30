package com.models;

import java.util.ArrayList;

public class RegisterCareTaker {
	ArrayList<RegisterMemento> memList = new ArrayList<RegisterMemento>();
	int index = memList.size();
	
	public void addMemento(RegisterMemento m) {
		memList.add(m);
	}
	
	public RegisterMemento getLastMemento() {
		RegisterMemento registerMemento = memList.get(memList.size() - 1);
		memList.remove(memList.size() - 1);
		return registerMemento;
	}
}
