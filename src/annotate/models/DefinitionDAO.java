package annotate.models;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.*;
import java.util.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class DefinitionDAO extends AbstractMongoDAO<Definition>{

    private final Mongo mongo;

    @Inject
    public DefinitionDAO(Morphia morphia, Mongo mongo){
        super(Definition.class, morphia);
        this.mongo = mongo;
    }

	
	public List<Definition> findByTerm(String term){
		Definition result = null;
		BasicDBObject query = new BasicDBObject();
        query.put("alternates", term);
		DBCursor cursor = collection().find(query);
        return toList(cursor);
	}


    @Override
    protected DBCollection collection() {
        return mongo.getDB("mydb").getCollection("definitions");
    }

}
