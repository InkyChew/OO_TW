package com.models;

import java.util.HashMap;

public class OrCtStored {
	public static OrCtStored orCtStored = null;
	HashMap<String, RegisterOriginator> OrMap = new HashMap<>();
	HashMap<String, RegisterCareTaker> CTMap = new HashMap<>();
	
	private OrCtStored() {
		
	}
	
	public static OrCtStored getInstance() {
		if (orCtStored == null){
            synchronized(OrCtStored.class){
                if(orCtStored == null) {
                	orCtStored = new OrCtStored();
                }
            }
        }
        return orCtStored;
	}
	
	public void removeOrCT(String key) {
		OrMap.remove(key);
		CTMap.remove(key);
	}
	
	public RegisterOriginator addRegisterOriginator(String key) {
		RegisterOriginator originator = new RegisterOriginator(key);
		OrMap.put(key, originator);
		return originator;
	}
	
	public RegisterCareTaker addRegisterCareTaker(String key) {
		RegisterCareTaker careTaker = new RegisterCareTaker();
		CTMap.put(key, careTaker);
		return careTaker;
	}
	
	public RegisterOriginator getRegisterOriginator(String key) {
		return OrMap.get(key);
	}
	
	public RegisterCareTaker getRegisterCareTaker(String key) {
		return CTMap.get(key);
	}
	
	public Boolean OrCTIDIsEmpty(String key) {
		if (getRegisterOriginator(key) == null) {
			return true;
		} else {
			return false;
		}
	}
}
