package annotate.ctrlrs;
import annotate.forms.*;
import annotate.models.*;
import annotate.models.dao.*;
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

@At("^/defs/$")
public class Words extends Controller{

	private static final Logger log = Logger.getLogger( DefinitionEdit.class );

	@Inject
	WordDAO words;

//     @Override
//     public WebResponse get(WebHit hit){
//     }

    @Override
    public WebResponse post(WebHit hit){
        String word = hit.getStrParam("word");
        String definition = hit.getStrParam("translation");
        Word w = new Word();
        w.setWord(word);
        w.setDefinition(definition);
		words.save(w);
		log.error("hey");
		return new StringWebResponse("ok");
    }

}
