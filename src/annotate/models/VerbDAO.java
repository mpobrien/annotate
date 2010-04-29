package annotate.models;
import java.util.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class VerbDAO extends AbstractMongoDAO<Verb>{

    private final Mongo mongo;

    @Inject
    public VerbDAO(Morphia morphia, Mongo mongo){
        super(Verb.class, morphia);
        this.mongo = mongo;
    }

    @Override
    protected DBCollection collection() {
        return mongo.getDB("mydb").getCollection("spanish_verbs");
    }

	public List<Verb> findByTerm(String term){
		BasicDBObject query = new BasicDBObject();
        query.put("conj", term);
		DBCursor cursor = collection().find(query);
        return toList(cursor);
	}
}
