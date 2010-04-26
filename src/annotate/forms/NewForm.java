package annotate.forms;
import com.mob.forms.*;
import com.mob.forms.widgets.*;

public class NewForm extends AbstractForm{

	private final StringField sf;

    public NewForm(){
		this.sf = string("text");
		sf.setWidget( WidgetGenerator.textArea(sf) );
    }

	public StringField getTextField(){
		return sf;
	}

	public void validate(){
		if( ( sf.getValue() + "" ).length() <= 0 ){
			error(sf, "text can not be blank.");
		}
		//pass
	}

}
