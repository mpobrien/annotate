package annotate.models;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class UserDAO extends AbstractMongoDAO<User>{

    private final Mongo mongo;

    @Inject
    public UserDAO(Morphia morphia, Mongo mongo){
        super(User.class, morphia);
        this.mongo = mongo;
    }

	public User getByUsername(String username){
		User result = null;
		BasicDBObject query = new BasicDBObject();
        query.put("username", username);
		BasicDBObject obj = (BasicDBObject)collection().findOne(query);
		if( obj != null ) {
			result = map(obj);
		}
		return result;
	}

	
    @Override
    protected DBCollection collection() {
        return mongo.getDB("mydb").getCollection("users");
    }

}
