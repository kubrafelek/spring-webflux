package com.reactive.springwebflux.repository;

import com.reactive.springwebflux.model.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CourseRepository extends ReactiveCrudRepository<Course, UUID> {
}
