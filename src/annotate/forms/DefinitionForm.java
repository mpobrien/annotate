package annotate.forms;
import annotate.models.*;
import com.mob.forms.*;
import org.apache.log4j.*;
import java.util.*;
import com.google.inject.*;
import com.google.common.collect.*;
import com.mob.forms.*;
import com.mob.forms.widgets.*;
import javax.servlet.http.*;
import java.util.regex.*;

public class DefinitionForm extends AbstractForm{
	private static final Logger log = Logger.getLogger( DefinitionForm.class );

	private static final Pattern definitionsPattern = Pattern.compile("definition_(\\d+)$");
	private static final int maxDefinitions = 10;

	private final StringField rootWord;
	private final StringField defId;
	private final EnumField<Definition.Gender> gender;
	private final EnumField<Definition.PartOfSpeech> partOfSpeech;
	private final ArrayList<StringField> definitions = new ArrayList<StringField>();

	public DefinitionForm(boolean addFirstField){
		super();
		this.partOfSpeech =  choices("part_of_speech", Definition.PartOfSpeech.class);
		this.gender =  choices("gender", Definition.Gender.class);
		this.rootWord = string("root_word");
		this.defId = string("id");
		this.defId.setRequired(false);
		this.defId.setWidget( WidgetGenerator.hidden(this.defId) );
		if(addFirstField){
			pushUsageField( pushDefinitionField() );
		}
	}

	public DefinitionForm(){
		this(true);
	}

	public StringField pushDefinitionField(){
		String fieldName = "definition_" + this.definitions.size();
		StringField def = string(fieldName);
		this.definitions.add(def);
		return def;
	}
	
	public StringField pushUsageField(StringField defField){
		StringField defUsage = string( defField.getName() + "_usage");
		defUsage.setRequired( false );
		return defUsage;
	}

	@Override
	public void validate(){
	}

	public void bind(Map<String,String[]> params){//{{{
		int numMatches = 0;
		for( String inputName : params.keySet()){
			Integer defNum = getDefNumber(inputName);
			if( defNum != null ){
				this.definitions.add( string("definition_" + defNum.toString() ) );
				StringField defUsage = string("definition_" + defNum.toString() + "_usage" );
				defUsage.setRequired( false );
			}
		}
		super.bind(params);
	}//}}}
	
	public StringField getRootWord(){    return rootWord;  }
	public EnumField<Definition.PartOfSpeech> getPartOfSpeech(){    return partOfSpeech;  }
	public EnumField<Definition.Gender> getGender(){    return gender;  }
	public ArrayList<StringField> getDefinitions(){    return definitions;  }

	public static Integer getDefNumber(String inputName){//{{{
		Matcher matcher = definitionsPattern.matcher(inputName);
		if( matcher.matches() ){
			try{
				return new Integer( matcher.group(1) );
			}catch(Exception e){
				return null;
			}
		}else{
			return null;
		}
	}//}}}

	public static DefinitionForm fromDefinition(Definition def){
		DefinitionForm form = new DefinitionForm(false);
		form.getRootWord().setValue( def.getRoot() );
		form.getPartOfSpeech().setValue( def.getPartOfSpeech() );
		form.getGender().setValue( def.getGender() );
		if( def.getExamples() != null ){
			for( UsageExample example : def.getExamples() ){
				StringField defField = form.pushDefinitionField();
				defField.setValue( example.getDefinition() );
				StringField usage = form.pushUsageField( defField );
				if( example.getExamples().size() > 0 ){
					usage.setValue( example.getExamples().get(0) );
				}
			}
		}
		return form;
	}
	
	public StringField getDefId(){    return defId;  }

}
