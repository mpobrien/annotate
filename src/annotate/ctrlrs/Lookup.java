package annotate.ctrlrs;
import annotate.forms.*;
import annotate.models.*;
import annotate.models.dao.*;
import com.mob.web.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;
import java.util.*;
import com.google.inject.*;
import com.google.common.collect.*;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

@At("^/lookup/?$")
public class Lookup extends Controller{
	Logger log = Logger.getLogger( Lookup.class );

	//VerbDAO verbs;
	@Inject
	WordDAO words;

	public String slug;

    @Override
    public WebResponse get(WebHit hit){
        String term = hit.getStrParam("term");
        List<Word> results = new ArrayList<Word>(0);
        if( term != null && term.length() > 0 ){
            results = words.lookUp(term);
        }
		if( results == null )
			return responses.json( new ArrayList<Word>(0) );
		else
			return responses.json(results);
    }


}
