package com.miskaris.laba3.observer;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

public interface Observer {
	void registerEvent(String message);
}
