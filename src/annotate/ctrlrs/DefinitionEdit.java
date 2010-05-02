package annotate.ctrlrs;
import annotate.forms.*;
import annotate.models.*;
import com.mob.web.*;
import com.mob.forms.*;
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

@At("^/words/(new|edit)/?$")
public class DefinitionEdit extends Controller{

	Logger log = Logger.getLogger( DefinitionEdit.class );

	@Inject
	DefinitionDAO defs;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){
		DefinitionForm form = new DefinitionForm();
		return responses.render("definition.html", ImmutableMap.of("form", form));
    }

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){
		DefinitionForm form = new DefinitionForm();
        form.bind( req );
		Definition d = new Definition();
		d.setPartOfSpeech( form.getPartOfSpeech().getValue() );
		d.setRoot( form.getRootWord().getValue() );
		d.setDefinition( form.getDefinition().getValue() );
		defs.save(d);
		return new StringWebResponse("");
		//return null;
    }

}
