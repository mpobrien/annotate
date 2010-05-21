package annotate.models;
import com.mob.web.*;

public class AnonymousUser extends User{

	public boolean getIsLoggedIn(){
		return false;
	}

}
