package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@MongoDocument
public class Definition extends AbstractMongoEntity{

	public enum PartOfSpeech{
		ADJECTIVE,
		VERB,
		NOUN,
		ADVERB,
		PRONOUN,
		INTERJECTION,
		CONJUNCTION
	};

    private String root;
    private String definition;
	@MongoEmbedded
    private List<UsageExample> examples;

	private String usageNote;
	private PartOfSpeech partOfSpeech;

	@MongoEmbedded
	private List<String> alternates;
    
    public String getRoot(){    return root;  }
    public void setRoot(String root){    this.root = root;  }
    
    public String getDefinition(){    return definition;  }
    public void setDefinition(String definition){    this.definition = definition;  }
    
    public String getUsageNote(){    return usageNote;  }
    public void setUsageNote(String usageNote){    this.usageNote = usageNote;  }
	
	public List<String> getAlternates(){    return alternates;  }
	public void setAlternates(List<String> alternates){    this.alternates = alternates;  }

	public PartOfSpeech getPartOfSpeech(){    return partOfSpeech;  }
	public void setPartOfSpeech(PartOfSpeech partOfSpeech){    this.partOfSpeech = partOfSpeech;  }

    public List<UsageExample> getExamples(){    return examples;  }
    public void setExamples(List<UsageExample> examples){    this.examples = examples;  }
}
