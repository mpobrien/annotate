package annotate.modules;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.google.inject.*;
import org.apache.log4j.*;

public class MongoModule extends AbstractModule{
	private static final Logger log = Logger.getLogger( MongoModule.class );

    public void configure(){
		try{
			Mongo m = new Mongo("127.0.0.1", 42126);
			bind(Mongo.class).toInstance(m);
		}catch(Exception e){
			log.error("couldn't get DB connection.", e);
		}
    }
}
