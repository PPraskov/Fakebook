package com.example.demo.repositories;

import com.example.demo.domain.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {
}
