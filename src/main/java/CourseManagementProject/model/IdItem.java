package CourseManagementProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract base class of all items with IDs
 * Supports getting ID, href URI, and class for serializing only id & href (prevent circular reference)
 */
@MappedSuperclass
abstract public class IdItem {
    private final String urlPrefix;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each item we store is related to a single apiKey (student team):
    @JsonIgnore
    private String apiKeyForItem;





    public IdItem(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getHref() {
        return urlPrefix + "/" + getId();
    }


    @JsonIgnore
    public String getApiKeyForItem() {
        return apiKeyForItem;
    }
    @JsonIgnore
    public void setApiKeyForItem(String apiKeyForItem) {
        this.apiKeyForItem = apiKeyForItem;
    }



    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        IdItem objAsIdItem = (IdItem) obj;
        return getId().equals(objAsIdItem.getId());
    }


    /**
     * Serialize only our ID and href (prevents circular references)
     * From http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
     */
    static public class IdItemSerializer extends StdSerializer<IdItem>
    {
        public IdItemSerializer() {
            this(null);
        }


        public IdItemSerializer(Class<IdItem> t) {
            super(t);
        }

        @Override
        public void serialize(
                IdItem item,
                JsonGenerator generator,
                SerializerProvider provider
        ) throws IOException, JsonProcessingException
        {
            IdHref info = new IdHref(
                                item.getId(),
                                item.getHref()
                        );
            generator.writeObject(info);
        }
    }

    /**
     * Serialize only our ID and href (prevents circular references)
     * From http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
     */
    static public class IdItemSetSerializer extends StdSerializer<Set<IdItem>>
    {
        public IdItemSetSerializer() {
            this(null);
        }


        public IdItemSetSerializer(Class<Set<IdItem>> t) {
            super(t);
        }

        @Override
        public void serialize(
                Set<IdItem> items,
                JsonGenerator generator,
                SerializerProvider provider)
                throws IOException, JsonProcessingException {

            Set <IdHref> info = new HashSet<>();
            for (IdItem item : items) {
                info.add(
                        new IdHref(
                                item.getId(),
                                item.getHref()
                        ));
            }
            generator.writeObject(info);
        }
    }


    static public class IdItemSetDisableDeserializer extends StdDeserializer<Set<IdItem>> {
        public IdItemSetDisableDeserializer() {
            this(null);
        }
        protected IdItemSetDisableDeserializer(Class<Set<IdItem>> t) {
            super(t);
        }

        @Override
        public Set<IdItem> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            // Consume this element in the parse tree, thus letting the rest of the object deserialize.
            // (Without this the text is left behind and prevents the rest of the object from deserializing)
            JsonNode node = p.getCodec().readTree(p);
            return new HashSet<>();
        }
    }

    static public class IdHref {
        public Long id;
        public String href;

        public IdHref(Long id, String href) {
            this.id = id;
            this.href = href;
        }
    }


}
