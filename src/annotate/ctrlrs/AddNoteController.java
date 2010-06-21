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

@At("^/snippets/([a-zA-Z0-9\\-]*)/newnote/?$")
public class AddNoteController extends Controller{
	Logger log = Logger.getLogger( ShowSnippetController.class );

	@Inject
	TextSnippetDAO snippets;

	@Inject
	TextNoteDAO notes;

	public String slug;

    @Override
    public WebResponse post(WebHit hit){
		this.slug = args.get(0);
		TextSnippet ts = snippets.findBySlug(this.slug);
		if( ts == null ){ return responses.render("404.html"); }

        NoteForm f = new NoteForm();
        f.bind(hit.getRequest());
        if( f.hasErrors() ){
			return responses.render("404.html");
        }else{
			TextNote newNote = new TextNote();
			newNote.setStartIndex( f.getStartIndex().getValue() );
			newNote.setEndIndex( f.getEndIndex().getValue() );
			//ts.getNotes().add(newNote);
			snippets.save(ts);
        }
		return responses.render("snippet.html", ImmutableMap.of("snippet", ts));
    }

    class NoteForm extends AbstractForm{//{{{
        private final IntegerField startIndex;
        private final IntegerField endIndex;

        public NoteForm(){
            this.startIndex = integer("startIndex");
            this.endIndex = integer("endIndex"); 
        }

        public IntegerField getStartIndex(){ return this.startIndex; }
        public IntegerField getEndIndex(){ return this.endIndex; }

        public void validate(){
            if( startIndex.getValue() != null && !(startIndex.getValue() > 0)){
                error( startIndex, "start index must be greater than 0");
            }
            if( endIndex.getValue() != null && !(endIndex.getValue() > 0)){
                error( endIndex, "end index must be greater than 0");
            }
            if( endIndex.getValue() != null && 
                startIndex.getValue()!= null &&
                !(endIndex.getValue() >= startIndex.getValue()) ){
                error( endIndex, "end index must be >= start index.");
            }
        }
    }//}}}

}
