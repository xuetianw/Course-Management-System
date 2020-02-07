package CourseManagementProject.controllers;

import CourseManagementProject.exception.ResourceNotFoundException;
import CourseManagementProject.model.Course;
import CourseManagementProject.model.Student;
import CourseManagementProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScoreBoardController {

    private UserService userService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ScoreBoardController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<Student> getAllPlayers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public Student findPlayer(@PathVariable("userId") int userId) {

        Student theStudent = userService.findById(userId);

        if (theStudent != null) {
            return theStudent;
        }

        throw new ResourceNotFoundException("player id does not existed - " + userId);

    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createNewPlayer(@RequestBody Student student) {
        student.setId(0);


        userService.save(student);
        return student;
    }

    @PutMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updatePlayer(@RequestBody Student student) {

        userService.save(student);
        return student;
    }

    @DeleteMapping("/players/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {

        Student theStudent = userService.findById(playerId);

        // throw exception if null

        if (theStudent == null) {
            throw new ResourceNotFoundException("player id not found - " + playerId);
        }

        userService.deleteById(playerId);

        return ("Deleted player id - " + playerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Student addNewUser(@RequestBody Student newStudent) {


        // Clear its current ID (as we are going to give it one):
        // (Failing to do this, and having the ID 0 causes exceptions when we try to add it to repository)
//        newStudent.setId(null);

        // Check it:
        if (newStudent.getEmail() == null || newStudent.getEmail().isEmpty()) {
            throw new ResourceNotFoundException("Student email address must not be empty.");
        }
        if (newStudent.getFirst_name() == null || newStudent.getLast_name().isEmpty()) {
            throw new ResourceNotFoundException("Student name must not be empty.");
        }
        if (newStudent.getPassword() == null || newStudent.getPassword().isEmpty()) {
            throw new ResourceNotFoundException("Student password must not be empty.");
        }

        // Already exist?
        List<Student> myStudents = userService.findAll();
        for (Student student : myStudents) {
            if (student != null
                    && student.getEmail().equals(newStudent.getEmail())
            ) {
                throw new InvalidLoginException("Student with same email address already created.");
            }
        }

        // Secure the password:
//        newStudent.setPassword(bCryptPasswordEncoder.encode(newStudent.getPassword()));

        // Add it to repository, returning what is actually created in DB
        userService.save(newStudent);
        return newStudent;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/login")
    public Student login(@RequestBody Student theStudent) {
        // Already exist?
        List<Student> myStudents = userService.findAll();
        for (Student student : myStudents) {
            if (student != null
                    && student.getEmail().equals(theStudent.getEmail())
                    && student.getPassword().equals(theStudent.getPassword())
            ) {
                return student;
            }
        }

        throw new InvalidLoginException("invalid credential.");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class InvalidLoginException extends RuntimeException {
        public InvalidLoginException(String s){
            super(s);
        }
    }

    @GetMapping("/users/{userId}/courses")
    public Student findPlayerCourses(@PathVariable("userId") int userId) {

        Student theStudent = userService.findById(userId);

        if (theStudent != null) {
            return theStudent;
        }

        throw new ResourceNotFoundException("player id does not existed - " + userId);
    }

}
