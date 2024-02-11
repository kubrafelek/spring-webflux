package com.reactive.springwebflux.dto;

import java.util.List;

public record StudentListDto (List<StudentDto> students) {
}
