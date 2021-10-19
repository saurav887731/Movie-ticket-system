package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

public class Theatre {
	
	private final String id;
    private final String name;
    private final String city;
    private final String address;
    private final List<Screen> screens; // how much screen this threatre have and could be added later on

    public Theatre(@NonNull final String id, @NonNull final String name , @NonNull final String city , @NonNull final String address) {
        this.id = id;
        this.name = name;
        this.city=city;
        this.address=address;
        this.screens = new ArrayList<>();
    }
    
    
    public String getCity() {
		return city;
	}


	public String getAddress() {
		return address;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void addScreen(@NonNull final  Screen screen) {
        screens.add(screen);
    }
}
