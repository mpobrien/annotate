package annotate.forms;
import annotate.models.*;
import com.mob.forms.*;
import org.apache.log4j.*;
import java.util.*;
import com.google.inject.*;
import com.google.common.collect.*;
import com.mob.forms.*;
import com.mob.forms.widgets.*;

public class DefinitionForm extends AbstractForm{
	private final StringField rootWord;
	private final StringField definition;
	private final EnumField<Definition.PartOfSpeech> partOfSpeech;

	public DefinitionForm(){
		this.partOfSpeech =  choices("part_of_speech", Definition.PartOfSpeech.class);
		this.rootWord = string("root_word");
		this.definition = string("definition"); 
	}

	public void validate(){
	}
	
	public StringField getRootWord(){    return rootWord;  }
	public StringField getDefinition(){    return definition;  }
	public EnumField<Definition.PartOfSpeech> getPartOfSpeech(){    return partOfSpeech;  }

}
