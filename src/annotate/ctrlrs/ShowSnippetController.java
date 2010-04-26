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

@At("^/snippets/([a-zA-Z0-9\\-]*)/?$")
public class ShowSnippetController extends Controller{
	Logger log = Logger.getLogger( ShowSnippetController.class );

	@Inject
	TextSnippetDAO snippets;

	public String slug;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
		this.slug = args.get(0);
		TextSnippet ts = snippets.findBySlug(this.slug);
		return responses.render("snippet.html", ImmutableMap.of("snippet", ts));
    }

}