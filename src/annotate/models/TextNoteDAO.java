package annotate.models;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class TextNoteDAO extends AbstractMongoDAO<TextNote>{

    private final Mongo mongo;

    @Inject
    public TextNoteDAO(Morphia morphia, Mongo mongo){
        super(TextNote.class, morphia);
        this.mongo = mongo;
    }

    @Override
    protected DBCollection collection() {
        return mongo.getDB("mydb").getCollection("notes");
    }

}
