package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

	Optional<Application> findByName(String applicationName);

}
