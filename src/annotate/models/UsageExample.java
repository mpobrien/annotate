package annotate.models;
import com.google.code.morphia.*;
import com.google.code.morphia.annotations.*;
import java.util.*;

@MongoDocument
public class UsageExample extends AbstractMongoEntity{

    private String usageText;
    private String translation;

    public String getUsageText(){    return usageText;  }
    public void setUsageText(String usageText){    this.usageText = usageText;  }
    
    public String getTranslation(){    return translation;  }
    public void setTranslation(String translation){    this.translation = translation;  }

}
