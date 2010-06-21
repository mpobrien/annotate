package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@Entity
public class TextSnippet{

	@Id
	private String id;
    private String text;
    private String title;
	private String slug;

	@Embedded
	private List<TextNote> notes;
    
    public String getText(){    return text;  }
    public void setText(String text){    this.text = text;  }
    
    public String getTitle(){    return title;  }
    public void setTitle(String title){    this.title = title;  }
	
	public String getSlug(){    return slug;  }
	public void setSlug(String slug){    this.slug = slug;  }

    //public List<TextNote> getNotes(){    return notes;  }
    //public void setNotes(List<TextNote> notes){    this.notes = notes;  }

	
	public String getId(){    return id;  }
	public void setId(String id){    this.id = id;  }
}
