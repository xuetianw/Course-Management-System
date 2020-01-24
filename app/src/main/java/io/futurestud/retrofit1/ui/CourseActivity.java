package io.futurestud.retrofit1.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.futurestud.retrofit1.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        populateListView();
    }

    private void populateListView() {

        String[] courses = {"asdf", "asdf"};

        // build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.course_items, courses);

        // configure the list view
        ListView list = (ListView) findViewById(R.id.course_listId);
        list.setAdapter(adapter);
    }
}
