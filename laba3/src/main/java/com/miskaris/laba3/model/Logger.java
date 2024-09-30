package com.miskaris.laba3.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "log_books")
@Data
@NoArgsConstructor
@IdClass(LoggerID.class)
@Component
@Scope (scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Logger {
	@Id
    @Column(name = "executed_at")
	private LocalDateTime timestamp;
	
	@Id
	@Column(name = "message", nullable = false)
	String message;	
	
	public void logMessage(String message)
	{
		setMessage(message);
		timestamp = LocalDateTime.now();
	}

}
