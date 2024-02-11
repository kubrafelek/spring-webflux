package com.reactive.springwebflux.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class SpringCourseMetadata extends CourseMetadata {

    private List<String> prerequisities;
    private String language;
    private String github;
}
