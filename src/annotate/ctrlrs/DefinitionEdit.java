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

@At("^/words/(new|edit)/$")
public class DefinitionEdit extends Controller{

	private static final Logger log = Logger.getLogger( DefinitionEdit.class );

	@Inject
	DefinitionDAO defs;

    @Override
    public WebResponse get(WebHit hit){
		String id = hit.getStrParam("id");
		DefinitionForm form = null;
		if( id.length() > 0 ){
			try{
				Definition d = defs.get(id);
				if( d != null ){
					form = DefinitionForm.fromDefinition(d);
				}
			}catch(Exception e){
				//todo: nothin
			}
		}
		if( form == null ){
			form = new DefinitionForm(false);
		}
		return responses.render("definition.html", ImmutableMap.of("form", form));
    }

    @Override
    public WebResponse post(WebHit hit){
		DefinitionForm form = new DefinitionForm(false);
        form.bind( hit.getRequest() );
		if( !form.hasErrors() ){
			Definition def;
            if( form.getDefId().getValue() != null && form.getDefId().getValue().length() > 0 ){
				def = defs.get(form.getDefId().getValue());
			}else{
				def = Definition.fromDefinitionForm( form );
				defs.save(def);
			}
			return responses.reverseRedirect(DefinitionEdit.class, 
											 ImmutableMap.of("id", def.getId()),
											 "edit");
		}else{
			log.error( form.getErrors() );
			return new StringWebResponse("");
		}
    }

}
