package CourseManagementProject.controllers;

import CourseManagementProject.model.Course;
import CourseManagementProject.Service.CourseService;
import CourseManagementProject.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/courses")
    public List<Course> getAllPlayers() {
        List<Course> temp = courseService.findAll();
        return temp;
    }

    @GetMapping("/courses/{courserId}")
    public Course findPlayer(@PathVariable int courseId) {

        Course theCourse = courseService.findById(courseId);

        if (theCourse != null) {
            return theCourse;
        }

        throw new ResourceNotFoundException("courses id does not existed - " + theCourse);

    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createNewCourse(@RequestBody Course course) {
        course.setId(0);

        courseService.save(course);
        return course;
    }

    @PutMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course updateCourse(@RequestBody Course course) {

        courseService.save(course);
        return course;
    }

    @DeleteMapping("/courses/{courserId}")
    public String deletePlayer(@PathVariable int courseId) {

        Course theUser = courseService.findById(courseId);

        // throw exception if null

        if (theUser == null) {
            throw new ResourceNotFoundException("course id not found - " + courseId);
        }

        courseService.deleteById(courseId);

        return ("Deleted course id - " + courseId);
    }


}
