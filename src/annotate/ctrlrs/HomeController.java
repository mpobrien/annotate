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

@At("^/?$")
public class HomeController extends Controller{

	private static final Logger log = Logger.getLogger( HomeController.class );

	@Inject
	TextSnippetDAO snippets;

	@Inject
	UserContext userContext;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
		List<TextSnippet> allSnippets = snippets.findAll(0,100);
		Map context = Maps.newHashMap();
		context.put( "snippets", allSnippets );
		return responses.render("listing.html", context);
    }

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){
		return get(req, res);
    }

}
