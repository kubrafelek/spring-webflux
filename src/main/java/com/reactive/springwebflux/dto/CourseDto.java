package com.reactive.springwebflux.dto;

import com.reactive.springwebflux.model.metadata.CourseMetadata;

public record CourseDto(
    String name,
    String description,
    Integer duration,
    String teacher,
    CourseMetadata courseMetadata) {
}
