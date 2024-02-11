package com.reactive.springwebflux.dto;

import java.util.List;

public record StudentDto(String name, String email, List<CourseDto> courses) {
}
