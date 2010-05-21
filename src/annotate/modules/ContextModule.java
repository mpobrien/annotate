package annotate.modules;
import com.mob.web.*;
import com.google.inject.*;
import org.apache.log4j.*;

public class ContextModule extends AbstractModule{

	private static final Logger log = Logger.getLogger( ContextModule.class );

    public void configure(){
		//bind(UserResolver.class).to(MongoUserResolver.class);
    }

}
