package com.reactive.springwebflux.service;

import com.reactive.springwebflux.dto.CourseDto;
import com.reactive.springwebflux.dto.StudentDto;
import com.reactive.springwebflux.dto.StudentListDto;
import com.reactive.springwebflux.model.Student;
import com.reactive.springwebflux.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<StudentListDto> findAllStudentWithCourses() {

        return studentRepository.findAll()
                .flatMap(student -> {
                    List<Mono<CourseDto>> courseDtoList = student.getCourses()
                            .stream()
                            .map(courseId -> courseService.findById(UUID.fromString(courseId)))
                            .collect(Collectors.toList());

                    return Flux.combineLatest(courseDtoList, objects -> {
                        List<CourseDto> courses = Arrays.stream(objects)
                                .map(obj -> (CourseDto) obj)
                                .collect(Collectors.toList());

                        return new StudentDto(
                                student.getName(),
                                student.getEmail(),
                                courses);
                    });
                })
                .collectList()
                .map(StudentListDto::new);
    }
}
