package annotate.models.dao;
import annotate.models.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class TextNoteDAO extends DAO<TextNote,String>{

    private final Mongo mongo;

    @Inject
    public TextNoteDAO(Morphia morphia, Mongo mongo){
        super(TextNote.class, mongo, morphia, "mydb");
        this.mongo = mongo;
    }

}
