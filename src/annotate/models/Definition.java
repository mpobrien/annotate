package annotate.models;
import org.apache.log4j.*;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import com.google.common.collect.*;
import java.util.*;
import com.mob.forms.*;
import annotate.forms.DefinitionForm;

@Entity
public class Definition{
	private static final Logger log = Logger.getLogger( Definition.class );

	public enum PartOfSpeech{//{{{
		Adjective,
		Verb,
		Noun,
		Adverb,
		Pronoun,
		Interjection,
		Conjunction
	};
//}}}

    public enum Gender{//{{{
		None,
		Masculine,
		Feminine
	}//}}}

	@Id
	private String id;
    private String root;
    private String definition;
	private Gender gender;

	@Embedded
    private List<UsageExample> examples;

	private String usageNote;
	private PartOfSpeech partOfSpeech;

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

	public Gender getGender(){    return gender;  }
	public void setGender(Gender gender){    this.gender = gender;  }

	public String getId(){    return id;  }
	public void setId(String id){    this.id = id;  }

	public static Definition fromDefinitionForm(DefinitionForm form){//{{{
		Definition d = new Definition();
		d.setPartOfSpeech( form.getPartOfSpeech().getValue() );
		d.setRoot( form.getRootWord().getValue() );

		//TODO use a better REGEX here
		d.setAlternates( ImmutableList.copyOf( form.getStringsMatching("alternate", "\\w+") ) );
		List<UsageExample> defs = Lists.newArrayList();
		for( StringField definition : form.getDefinitions() ){
			UsageExample ue = new UsageExample();
			ue.setDefinition( definition.getValue() );
			if( form.getFields().containsKey( definition.getName() + "_usage" ) ){
				ue.setExamples( ImmutableList.of( ((StringField)form.getFields().get(definition.getName() + "_usage")).getValue() ) ); 
			}else{
				ue.setExamples( new ArrayList<String>() );
			}
			defs.add( ue );
		}
		d.setExamples( defs );
		return d;
	}//}}}
	
	
}
