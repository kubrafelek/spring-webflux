package com.reactive.springwebflux.router;

import com.reactive.springwebflux.dto.StudentListDto;
import com.reactive.springwebflux.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

    private final StudentService studentService;

    public StudentHandler(StudentService studentService) {
        this.studentService = studentService;
    }

    public Mono<ServerResponse> handleFindAllStudentWithCourses(ServerRequest serverRequest) {

        return studentService.findAllStudentWithCourses()
                .flatMap(studentListDto -> ServerResponse.ok()
                        .bodyValue(studentListDto))
                .switchIfEmpty(
                        Mono.defer(() -> Mono.error(new RuntimeException("No records found"))));


    }
}
