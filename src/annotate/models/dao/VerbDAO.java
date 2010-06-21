package annotate.models.dao;
import annotate.models.*;
import java.util.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class VerbDAO extends DAO<Verb,String>{

    @Inject
    public VerbDAO(Morphia morphia, Mongo mongo){
        super(Verb.class, mongo, morphia, "spanish_verbs");
    }

	public List<Verb> findByTerm(String term){
		return find(createQuery().filter("conj =", term)).asList();
	}
}
