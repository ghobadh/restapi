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
@RequestMapping("/students")
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




    @GetMapping
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "John", "Doe"));
        students.add(new Student(2L, "Jane", "Doe"));
        students.add(new Student(3L, "Jack", "Gargamel"));
        students.add(new Student(4L, "Parker", "Gargamel"));

        return students;
    }

    //This is similar to the other method in above but I try to embrace the response with ResponseEntity class
    @GetMapping("/students_response")
    public ResponseEntity<List<Student>> getStudents_response(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "John", "Doe"));
        students.add(new Student(2L, "Jane", "Doe"));
        students.add(new Student(3L, "Jack", "Gargamel"));
        students.add(new Student(4L, "Parker", "Gargamel"));

        return ResponseEntity.ok().body(students);
    }

    //Spring Boot REST API with PAth Variable
    @GetMapping("/{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName){
        return ResponseEntity.ok(new Student((long) studentId, firstName, lastName));

    }

    //Spring Boot REST API with Request Param
    //example: http://localhost:8080/students/query?id=1&firstname=Gargamel&lastname=Izreal
    @GetMapping("/query")
    public ResponseEntity<Student> getStudentById(@RequestParam("id")  long studentId,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname){
        Student student = new Student(studentId, firstname, lastname);
        return ResponseEntity.ok().body(student);

    }

        //Spring Boot REST API that handle HTTP POST Request - Creating new resource
        // @PostMapping and @RequestBody
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName() );
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //String Boot REST API that handle HTTP PUT Request - Updating existing Resoure
    //example: http://localhost:8080/students/76/update
    @PutMapping("/{id}/update")
    //@ResponseStatus(HttpStatus.OK) <-- Since I added ResponseEntity class and I can put
    // the HTTP 200 with ok() method , I don't need this anymore
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Long studentID){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        student.setId(77L);
        return ResponseEntity.ok(student);
    }


    //String Boot REST API that handle HTTP DELETE Request - deleting existing resource
    //example: http://localhost:8080/students/76/delete
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentID){
        return ResponseEntity.ok("Student with id " + studentID + " deleted");
    }

}
