package annotate.models;
import com.google.code.morphia.*;
import java.util.*;
import com.google.code.morphia.annotations.*;

@MongoDocument
public class Verb extends AbstractMongoEntity{
	
    private String infinitive;
    private List<String> conj;
    
    public String getInfinitive(){    return infinitive;  }
    public void setInfinitive(String infinitive){    this.infinitive = infinitive;  }

    public List<String> getConj(){    return conj;  }
    public void setConj(List<String> conjugations){    this.conj= conj;  }

}
