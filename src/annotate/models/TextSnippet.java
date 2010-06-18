package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@MongoDocument
public class TextSnippet extends AbstractMongoEntity{

    private String text;
    private String title;
	private String slug;

	@MongoEmbedded
	private List<TextNote> notes;
    
    public String getText(){    return text;  }
    public void setText(String text){    this.text = text;  }
    
    public String getTitle(){    return title;  }
    public void setTitle(String title){    this.title = title;  }
	
	public String getSlug(){    return slug;  }
	public void setSlug(String slug){    this.slug = slug;  }

    //public List<TextNote> getNotes(){    return notes;  }
    //public void setNotes(List<TextNote> notes){    this.notes = notes;  }

}
