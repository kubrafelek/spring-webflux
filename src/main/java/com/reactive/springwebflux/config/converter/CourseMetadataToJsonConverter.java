package com.reactive.springwebflux.config.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.springwebflux.model.metadata.CourseMetadata;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.io.IOException;

@WritingConverter
@Slf4j
public class CourseMetadataToJsonConverter implements Converter<CourseMetadata, Json> {

    private final ObjectMapper objectMapper;

    public CourseMetadataToJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json convert(CourseMetadata courseMetadata) {
        try {
            return Json.of(objectMapper.writeValueAsBytes(courseMetadata));
        } catch (IOException e) {
            log.error("Error while converting CourseMetadata to Json", e);
            throw new RuntimeException(e);
        }
    }
}
