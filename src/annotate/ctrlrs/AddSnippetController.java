package annotate.ctrlrs;
import annotate.forms.*;//{{{
import java.net.*;
import java.io.*;
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
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.*;//}}}

@At("^/new/$")
public class AddSnippetController extends Controller{
	private static final Logger log = Logger.getLogger( AddSnippetController.class );
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	@Inject
	TextSnippetDAO snippets;
	@Inject
	FlashProvider flash;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
		Map context = Maps.newHashMap();
		NewForm f = new NewForm();
		context.put("form", f);
		return responses.render("home.html", context);
    }

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){
		Flash fl = flash.get();
		NewForm f = new NewForm();
		f.bind( req );
		f.validate();
		if( !f.hasErrors() ){
			TextSnippet ts = new TextSnippet();
			ts.setText(f.getTextField().getValue()); 
			ts.setTitle(f.getTitleField().getValue());
			ts.setSlug( slugify( ts.getTitle() ) );
			snippets.save(ts);

			//TODO handle duplicate slug if necessary?

			//SessionMessage sm = SessionMessage.getInstance(req, res);
			fl.put( "success", ShowSnippetController.SUCCESS_NEW );
			return responses.reverseRedirect( ShowSnippetController.class, ts.getSlug() );
		}else{
			return responses.render("home.html", ImmutableMap.of("form", f) );
		}
    }

	public static String slugify(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}

//     public static String slugify(String input) throws UnsupportedEncodingException {//{{{
//         if (input == null || input.length() == 0) return "";
//         String toReturn = normalize(input);
//         toReturn = toReturn.replace(" ", "-");
//         toReturn = toReturn.toLowerCase();
//         toReturn = URLEncoder.encode(toReturn, "UTF-8");
//         return toReturn;
//     }//}}}
//  
//     private static String normalize(String input) {//{{{
//         if (input == null || input.length() == 0) return "";
//         return Normalizer.normalize(input, Form.NFD).replaceAll("[^\\p{ASCII}]","");
//     }//}}}
// 
}
