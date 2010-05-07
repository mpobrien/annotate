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

@At("^/login/?$")
public class LoginController extends Controller{

	private static final Logger log = Logger.getLogger( RegisterController.class );
	public static final String TEMPLATE = "login.html";

	@Inject
    UserDAO users;

    @Override
    public WebResponse get(HttpServletRequest req, HttpServletResponse res){//{{{
		LoginForm form = new LoginForm();
		return responses.render(TEMPLATE, ImmutableMap.of("form", form));
	}//}}}

    @Override
    public WebResponse post(HttpServletRequest req, HttpServletResponse res){//{{{
		LoginForm form = new LoginForm();
		form.bind(req);
		form.validate();
		Map context = ImmutableMap.of("form", form);
		if( form.hasErrors() ){
			return responses.render(TEMPLATE, context);
		}else{
			User testUser = users.getByUsername( form.getUsername().getValue() );
			if( testUser == null ){
				form.error("db", "Username doesn't exist");
				return responses.render(TEMPLATE, context);
			}else{
				if( !testUser.checkPassword( form.getPassword().getValue() ) ){
					form.error(form.getPassword(), "Invalid password");
					return responses.render(TEMPLATE, context);
				}else{
					return new StringWebResponse("yeah");
				}
			}
		}
    }//}}}

    class LoginForm extends AbstractForm{//{{{
        private final StringField username;
        private final StringField password;

        public LoginForm(){
            this.username = string("username");
            this.password = string("password");
			this.password.setWidget( WidgetGenerator.password(this.password) );
        }
		
		public void validate(){
		}

        public StringField getUsername(){    return username;  }
        public StringField getPassword(){    return password;  }

    }//}}}
        
}
