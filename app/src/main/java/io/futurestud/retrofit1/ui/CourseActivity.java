package io.futurestud.retrofit1.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.futurestud.retrofit1.R;
import io.futurestud.retrofit1.api.model.Course;
import io.futurestud.retrofit1.api.model.Student;
import io.futurestud.retrofit1.api.proxy.ProxyBuilder;
import io.futurestud.retrofit1.api.proxy.WGServerProxy;
import retrofit2.Call;

public class CourseActivity extends AppCompatActivity {
    private WGServerProxy proxy;
    EditText courseET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        proxy = ProxyBuilder.getProxy();
        courseET = (EditText) findViewById(R.id.add_courseETId);

        setup_add_btn();

        populateListView();
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, CourseActivity.class);
    }

    private void setup_add_btn() {
        Button button = (Button) findViewById(R.id.save_buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int id = getStudent().getId();
                int id = 2;
                Call<Student> call = proxy.getUser(id);
                ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> add_courseresponse(returnedKey));
            }
        });
    }

    private void add_courseresponse(Student response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            Student tempStudent = response;
            Course tempCourse1 = new Course(courseET.getText().toString());

            tempStudent.getCourses().add(tempCourse1);

            // add student to courses
            tempCourse1.addStudent(tempStudent);

            Call<Course> call = proxy.add_course(tempCourse1);
            ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> add_courseresponse(returnedKey));
        }
    }

    private void populateListView() {
        int id = 2;
        Call<Student> call = proxy.getUser(id);
        ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> response_listView(returnedKey));
    }

    private void response_listView(Student response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            List<String> courses_str_list = new ArrayList<>();

            Student student = response;
            if (response.getCourses() == null) {
                Toast.makeText(getApplicationContext(), "no courses registered",
                        Toast.LENGTH_LONG).show();
            } else {
                for (Course course : student.getCourses()) {
                    String temp = String.format("course id : %s \ncourse title : %s", course.getId(), course.getTitle());
                    courses_str_list.add(temp);
                }
            }

            // build adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.course_items, courses_str_list);

            // configure the list view
            ListView list = (ListView) findViewById(R.id.course_listId);
            list.setAdapter(adapter);
        }
    }

    private void add_courseresponse(Course response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "course added",
                    Toast.LENGTH_LONG).show();
            populateListView();
        }
    }

    private void response(List<Course> response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            List<String> courses_str_list = new ArrayList<>();

            for (Course course : response) {
                String temp = String.format("course id : %s \ncourse title : %s", course.getId(), course.getTitle());
                courses_str_list.add(temp);
            }

            // build adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.course_items, courses_str_list);

            // configure the list view
            ListView list = (ListView) findViewById(R.id.course_listId);
            list.setAdapter(adapter);
        }
    }
}
