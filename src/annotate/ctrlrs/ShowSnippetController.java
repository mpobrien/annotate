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

	public static final String SUCCESS_NEW = "new_snippet_created";

	@Inject
	TextSnippetDAO snippets;

	public String slug;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
        SessionMessage sm = SessionMessage.getInstance(req, res);
        Map context = Maps.newHashMap();


		if( SUCCESS_NEW.equals(sm.get("success") + "") ){
			context.put("success", "new");
		}else{
			context.put("success", "");
		}

		this.slug = args.get(0);
		TextSnippet ts = snippets.findBySlug(this.slug);
		if( ts == null ){
			return responses.render("404.html");
		}
		context.put("snippet", ts);
		return responses.render("snippet.html", context);
    }

}
