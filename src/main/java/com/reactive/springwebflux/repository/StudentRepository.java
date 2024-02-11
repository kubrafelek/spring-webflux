package com.reactive.springwebflux.repository;

import com.reactive.springwebflux.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface StudentRepository extends ReactiveCrudRepository<Student, UUID> {
}
