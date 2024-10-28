package com.miskaris.laba3.service;

import org.springframework.stereotype.Service;

import com.miskaris.laba3.model.Logger;
import com.miskaris.laba3.repository.LoggerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoggerService {
	private final LoggerRepository loggerRepository;  
	
	public void createLog(Logger log)
	{
		loggerRepository.save(log);
	}
}
