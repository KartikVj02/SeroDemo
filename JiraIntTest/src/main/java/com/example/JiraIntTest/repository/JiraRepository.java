package com.example.JiraIntTest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JiraIntTest.entity.JiraRequest;

@Repository
public interface JiraRepository extends JpaRepository<JiraRequest, Long> {
}