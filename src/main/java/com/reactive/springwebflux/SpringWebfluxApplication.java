package com.reactive.springwebflux;

import com.reactive.springwebflux.model.Course;
import com.reactive.springwebflux.model.Student;
import com.reactive.springwebflux.model.metadata.SpringCourseMetadata;
import com.reactive.springwebflux.repository.CourseRepository;
import com.reactive.springwebflux.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class SpringWebfluxApplication implements CommandLineRunner {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public SpringWebfluxApplication(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = Course.builder()
                .id(UUID.randomUUID())
                .name("Spring WebFlux")
                .description("Spring WebFlux")
                .duration(10)
                .teacher("John Doe")
                .courseMetadata(
                        SpringCourseMetadata.builder()
                                .type("spring")
                                .language("java")
                                .github("https://github.com/johndoe")
                                .prerequisities(List.of("Java","Spring"))
                                .build())
                .isUpdated(false)
                .build();

        courseRepository.save(course).block(); // void method bir şey dönmesin değer oluşsun diye istediğimiz için bunu yaptık

        Student student = Student.builder()
                .id(UUID.randomUUID())
                .name("Johnny")
                .email("johndoe@com")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .courses(Set.of(course.getId().toString()))
                .isUpdated(false)
                .build();

        studentRepository.save(student).block();
    }
}
