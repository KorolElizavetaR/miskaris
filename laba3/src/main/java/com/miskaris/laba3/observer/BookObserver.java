package com.miskaris.laba3.observer;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.miskaris.laba3.model.Logger;
import com.miskaris.laba3.service.BookService;
import com.miskaris.laba3.service.LoggerService;

import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookObserver implements Observer{
	private final LoggerService loggerService;
	private final ApplicationContext context;
	
	@Override
	public void registerEvent(String message) {
		Logger logger = context.getBean(Logger.class);
		logger.logMessage(message);
		loggerService.createLog(logger);
	}

}
