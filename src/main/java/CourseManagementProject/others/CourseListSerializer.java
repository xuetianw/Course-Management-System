package CourseManagementProject.others;

import CourseManagementProject.model.Course;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseListSerializer extends StdSerializer<List<Course>> {

    public CourseListSerializer() {
        this(null);
    }

    public CourseListSerializer(Class<List<Course>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Course> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Integer> ids = new ArrayList<>();
        for (Course item : items) {
            ids.add(item.getId());
        }
        generator.writeObject(ids);
    }
}