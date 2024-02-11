package com.reactive.springwebflux.config.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.springwebflux.model.metadata.CourseMetadata;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.io.IOException;

@ReadingConverter
@Slf4j
public class JsonToCourseMetadataConverter implements Converter<Json, CourseMetadata> {

    private final ObjectMapper objectMapper;

    public JsonToCourseMetadataConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CourseMetadata convert(Json json) {
        try {
            return objectMapper.readValue(json.asString(), CourseMetadata.class);
        } catch (IOException e) {
            log.error("Error while converting Json to CourseMetadata", e);
            throw new RuntimeException(e);
        }
    }
}
