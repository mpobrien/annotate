package annotate.models.dao;
import annotate.models.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class TextSnippetDAO extends DAO<TextSnippet,String>{

    @Inject
    public TextSnippetDAO(Morphia morphia, Mongo mongo){
        super(TextSnippet.class, mongo, morphia, "mydb");
    }

	
	public TextSnippet findBySlug(String slug){
		return find( createQuery().filter("slug =", slug) ).get();
	}

}
