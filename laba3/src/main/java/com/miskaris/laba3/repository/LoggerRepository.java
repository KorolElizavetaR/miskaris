package com.miskaris.laba3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miskaris.laba3.model.Logger;
import com.miskaris.laba3.model.LoggerID;


@Repository
public interface LoggerRepository extends JpaRepository<Logger, LoggerID>{

}
