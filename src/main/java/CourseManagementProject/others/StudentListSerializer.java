package CourseManagementProject.others;

import CourseManagementProject.model.Student;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentListSerializer extends StdSerializer<List<Student>> {

    public StudentListSerializer() {
        this(null);
    }

    public StudentListSerializer(Class<List<Student>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Student> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Integer> ids = new ArrayList<>();
        for (Student item : items) {
            ids.add(item.getId());
        }
        generator.writeObject(ids);
    }
}