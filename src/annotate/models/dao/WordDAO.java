package annotate.models.dao;
import annotate.models.*;
import java.util.*;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.*;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.*;
import com.google.inject.*;

public class WordDAO extends DAO<Word,String>{

    @Inject
    public WordDAO(Morphia morphia, Mongo mongo){
        super(Word.class, mongo, morphia, "words");
    }

	public List<Word> lookUp(String word){
		return find( createQuery().filter("word =", word) ).asList();
	}

}
