package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;

@Entity
public class TextNote{
	
	@Id
	private String id;
	private int startIndex;
	private int endIndex;

	public int getStartIndex(){    return startIndex;  }
	public void setStartIndex(int startIndex){    this.startIndex = startIndex;  }
	
	public int getEndIndex(){    return endIndex;  }
	public void setEndIndex(int endIndex){    this.endIndex = endIndex;  }

	
	public String getId(){    return id;  }
	
	public void setId(String id){    this.id = id;  }
}
