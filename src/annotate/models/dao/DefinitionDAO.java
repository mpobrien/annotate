package annotate.models.dao;
import annotate.models.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.*;
import com.google.code.morphia.query.*;
import java.util.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class DefinitionDAO extends DAO<Definition,String>{

    @Inject
    public DefinitionDAO(Morphia morphia, Mongo mongo){
        super(Definition.class, mongo, morphia, "mydb");
    }

	
	public List<Definition> findByTerm(String term){
		return find( createQuery().filter("alternates =", term) ).asList();
	}

}
