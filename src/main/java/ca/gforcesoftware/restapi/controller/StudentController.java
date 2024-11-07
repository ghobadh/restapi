package ca.gforcesoftware.restapi.controller;

import ca.gforcesoftware.restapi.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gavinhashemi on 2024-11-06
 */
@RestController
public class StudentController {


    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student(
                1L,
                "John",
                "Doe"
        );
        return student;
    }

    @GetMapping("/student_response")
    public ResponseEntity<Student> getStudentResponse(){
        Student student = new Student(
                1L,
                "John",
                "Doe"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK); <-- This line and next two lines are completely the same
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "custom-value")
                .body(student);

    }




    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "John", "Doe"));
        students.add(new Student(2L, "Jane", "Doe"));
        students.add(new Student(3L, "Jack", "Gargamel"));
        students.add(new Student(4L, "Parker", "Gargamel"));

        return students;
    }

    //Spring Boot REST API with PAth Variable
    @GetMapping("students/{id}/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName){
        return new Student((long) studentId, firstName, lastName);

    }

    //Spring Boot REST API with Request Param
    //example: http://localhost:8080/students/query?id=1&firstname=Gargamel&lastname=Izreal
    @GetMapping("/students/query")
    public Student getStudentById(@RequestParam("id")  long studentId,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname){
        return new Student(studentId, firstname, lastname);
    }

        //Spring Boot REST API that handle HTTP POST Request - Creating new resource
        // @PostMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName() );
        System.out.println(student.getLastName());
        return student;
    }

    //String Boot REST API that handle HTTP PUT Request - Updating existing Resoure
    //example: http://localhost:8080/students/76/update
    @PutMapping("students/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long studentID){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        student.setId(77L);
        return student;
    }


    //String Boot REST API that handle HTTP DELETE Request - deleting existing resource
    //example: http://localhost:8080/students/76/delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long studentID){
        return "Student with id " + studentID + " deleted";
    }

}
