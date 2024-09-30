package com.miskaris.laba3.observer;

import jakarta.persistence.Entity;

@Entity
public class BookObserver implements Observer{
	

	@Override
	public String registerEvent() {
		return null;
	}

}
