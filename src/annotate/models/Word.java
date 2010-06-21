package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@Entity
public class Word {

	@Id
	private String id;

	@Indexed
    private String word;
    private String definition;
    
    public String getWord(){    return word;  }
    public void setWord(String word){    this.word = word;  }
    
    public String getDefinition(){    return definition;  }
    public void setDefinition(String definition){    this.definition = definition;  }

	public String getId(){    return id;  }
	public void setId(String id){    this.id = id;  }
}
