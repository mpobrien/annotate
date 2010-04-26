package annotate.models;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class TextSnippetDAO extends AbstractMongoDAO<TextSnippet>{

    private final Mongo mongo;

    @Inject
    public TextSnippetDAO(Morphia morphia, Mongo mongo){
        super(TextSnippet.class, morphia);
        this.mongo = mongo;
    }

	
	public TextSnippet findBySlug(String slug){
		TextSnippet result = null;
		BasicDBObject query = new BasicDBObject();
        query.put("slug", slug);
		BasicDBObject obj = (BasicDBObject)collection().findOne(query);
		if( obj != null ) {
			result = map(obj);
		}
		return result;
	}


    @Override
    protected DBCollection collection() {
        return mongo.getDB("mydb").getCollection("snippets");
    }

}
