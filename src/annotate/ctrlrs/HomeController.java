package annotate.ctrlrs;
import annotate.forms.*;
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

@At("^/$")
public class HomeController extends Controller{
	Logger log = Logger.getLogger( HomeController.class );

	@Inject
	Mongo mng;
	DB db;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
		DB db = mng.getDB("mydb");

		DBCollection coll = db.getCollection("testCollection");
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

		Map context = Maps.newHashMap();
		NewForm f = new NewForm();
		context.put("form", f);
		System.out.println(f.getField("text").getWidget().getHtml());
		return responses.render("home.html", context);
    }

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){
		NewForm f = new NewForm();
		f.bind( req );
		f.validate();
		if( !f.hasErrors() ){
			DB db = mng.getDB("mydb");
			DBCollection coll = db.getCollection("testCollection");
			BasicDBObject doc = new BasicDBObject();
			doc.put("text", f.getTextField().getValue());
			coll.insert(doc);
		}
		
		return responses.reverseRedirect(HomeController.class);
    }

}
