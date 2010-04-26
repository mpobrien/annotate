package annotate.forms;
import com.mob.forms.*;
import com.mob.forms.widgets.*;

public class NewForm extends AbstractForm{

	private final StringField sf;
	private final StringField title;

    public NewForm(){
		this.title = string("title");
		this.sf = string("text");
		sf.setWidget( WidgetGenerator.textArea(sf) );
    }

	public StringField getTextField(){ return sf; }
	public StringField getTitleField(){ return title; }

	public void validate(){
		if( ( sf.getValue() + "" ).length() <= 0 ){
			error(sf, "text can not be blank.");
		}
		if( ( title.getValue() + "" ).length() <= 0 ){
			error(sf, "text can not be blank.");
		}
		//pass
	}

}
