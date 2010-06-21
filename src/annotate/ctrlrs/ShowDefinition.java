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

@At("^/words/?$")
public class ShowDefinition extends Controller{
	Logger log = Logger.getLogger( ShowDefinition.class );

	@Inject
	DefinitionDAO defs;

    @Override
    public WebResponse get(WebHit hit){
		String term = hit.getStrParam("term");
        List<Definition> results = new ArrayList<Definition>(0);
        if( term != null && term.length() > 0 ){
            results = defs.findByTerm(term);
        }
		return responses.render("definition.html", ImmutableMap.of("definitions", results));
    }

}
