package com.miskaris.laba3.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class LoggerID {
	 private LocalDateTime timestamp;
	 private String message;
}
