package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@Entity
public class UsageExample{

	private String definition;
	private List<String> examples;

	public List<String> getExamples(){    return examples;  }
	public void setExamples(List<String> examples){    this.examples = examples;  }
	
	public String getDefinition(){    return definition;  }
	public void setDefinition(String definition){    this.definition = definition;  }
}
