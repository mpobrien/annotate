package annotate.ctrlrs;
import annotate.forms.*;
import annotate.models.*;
import com.mob.web.*;
import com.mob.forms.*;
import com.mob.forms.widgets.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;
import java.util.*;
import com.google.inject.*;
import com.google.common.collect.*;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

@At("^/register/?$")
public class RegisterController extends Controller{

	private static final Logger log = Logger.getLogger( RegisterController.class );
	public static final String TEMPLATE = "register.html";

	@Inject
    UserDAO users;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){//{{{
		RegisterForm form = new RegisterForm();
		return responses.render(TEMPLATE, ImmutableMap.of("form", form));
	}//}}}

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){//{{{
		RegisterForm form = new RegisterForm();
		form.bind(req);
		form.validate();
		Map context = ImmutableMap.of("form", form);
		if( form.hasErrors() ){
			return responses.render(TEMPLATE, context);
		}else{
			User testUser = users.getByUsername( form.getUsername().getValue() );
			if( testUser != null ){
				form.error("db", "This username is already taken. :(");
				return responses.render(TEMPLATE, context);
			}else{
				try{
					User newUser = createNewUser( form.getUsername().getValue(), form.getPassword().getValue() );
					users.save( newUser );
					return new StringWebResponse("yeah");
				}catch(Exception e){
					form.error("db", "This username is already taken. :(");
					log.error("User could not be stored :(", e);
					return responses.render(TEMPLATE, context);
				}
			}
		}
    }//}}}

    class RegisterForm extends AbstractForm{//{{{
        private final StringField username;
        private final StringField password;
        private final StringField password2;

        public RegisterForm(){
            this.username = string("username");
            this.password = string("password");
			this.password.setWidget( WidgetGenerator.password(this.password) );
            this.password2 = string("password2");
			this.password2.setWidget( WidgetGenerator.password(this.password2) );
        }

        public StringField getUsername(){    return username;  }
        public StringField getPassword(){    return password;  }
        public StringField getPassword2(){    return password2;  }

        public void validate(){//{{{
			if( username.getValue() == null || !username.getValue().matches("^\\w\\w\\w\\w\\w*$") ){
				error( this.username, "This username is invalid. It must be at least 4 alphanumeric characters.");
			}

			if( this.password.getValue() == null || 
				this.password2.getValue() == null ||
				!this.password.getValue().equals( this.password2.getValue() ) ){
				error( this.password2, "Passwords must match.");
			}else{
				if( this.password.getValue().length() < 6 || this.password2.getValue().length() < 6 ){
					error( this.password2, "Password must be at least 6 characters long.");
				}
			}
        }//}}}
    }//}}}
        
	public User createNewUser(String username, String password) throws Exception{//{{{
		User u = new User();
		u.setUsername(username);
		u.resetPassword( password );
		return u;
	}//}}}

}
