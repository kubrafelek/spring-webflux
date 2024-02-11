package com.reactive.springwebflux.service;

import com.reactive.springwebflux.dto.CourseDto;
import com.reactive.springwebflux.model.Course;
import com.reactive.springwebflux.repository.CourseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<CourseDto> findById(UUID id) {
        return courseRepository.findById(id)
                .map(course ->
                        new CourseDto(
                                course.getName(),
                                course.getDescription(),
                                course.getDuration(),
                                course.getTeacher(),
                                course.getCourseMetadata()
                        )
                );
    }
}
