package annotate.ctrlrs;
import annotate.forms.*;
import annotate.models.*;
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

@At("^/verbs/?$")
public class VerbLookup extends Controller{
	Logger log = Logger.getLogger( VerbLookup.class );

	@Inject
	VerbDAO verbs;

	public String slug;

    @Override
    public WebResponse get(WebHit hit){
        String term = hit.getStrParam("term");
        List<Verb> results = new ArrayList<Verb>(0);
        if( term != null && term.length() > 0 ){
            results = verbs.findByTerm(term);
        }
		return responses.json(results);
    }


}
