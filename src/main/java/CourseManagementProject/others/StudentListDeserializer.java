package CourseManagementProject.others;

import CourseManagementProject.model.Student;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentListDeserializer extends StdDeserializer<List<Student>> {
    public StudentListDeserializer() {
        this(null);
    }
    protected StudentListDeserializer(Class<List<Student>> t) {
        super(t);
    }

    @Override
    public List<Student> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Consume this element in the parse tree, thus letting the rest of the object deserialize.
        // (Without this the text is left behind and prevents the rest of the object from deserializing)
        JsonNode node = p.getCodec().readTree(p);
        return new ArrayList<>();
    }
}
