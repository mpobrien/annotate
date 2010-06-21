package annotate.models;
import com.google.code.morphia.*;
import java.util.*;
import com.google.code.morphia.annotations.*;
//import com.mob.web.Util.Pair;

@Entity
public class Verb {

// 	public static final ImmutableList conjListing =
// 		ImmutableList.of(
// 			new Pair<Tense, Pronoun>(PRESENT,YO),
// 			new Pair<Tense, Pronoun>(PRESENT,TU),
// 			new Pair<Tense, Pronoun>(PRESENT,EL_ELLA_UD),
// 			new Pair<Tense, Pronoun>(PRESENT,NOS),
// 			new Pair<Tense, Pronoun>(PRESENT,VOS),
// 			new Pair<Tense, Pronoun>(PRESENT,ELS_UDS),

	public enum Tense{//{{{
		PRESENT(6),
		IMPERFECT(6),
		PRETERITE(6),
		FUTURE(6),
		CONDITIONAL(6),
		IMPERATIVE(5),
		PRESENT_SUB(6),
		IMPERFECT_SUB(6),
		GERUND(1),
		PAST_PARTICIPLE(1);

		private Integer numTerms;
		Tense(Integer numTerms){ this.numTerms = numTerms; }
		public Integer getNumTerms(){ return this.numTerms; }
	}//}}}
	public enum Pronoun{ YO, TU, EL_ELLA_UD, NOS, VOS, ELS_UDS; }

	@Id
	private String id;
    private String infinitive;
    private List<String> conj;

	@Transient
	private String origSource;
    
    public String getInfinitive(){    return infinitive;  }
    public void setInfinitive(String infinitive){    this.infinitive = infinitive;  }

    public List<String> getConj(){    return conj;  }
    public void setConj(List<String> conjugations){    this.conj= conj;  }
	
	public String getOrigSource(){    return origSource;  }
	public void setOrigSource(String origSource){    this.origSource = origSource;  }

// 	public List<String> getMatchedConj(){
// 		if( origSource == null || origSource.length() == 0 )
// 			return Lists.newArrayList(); 
// 		for( 
// 	}
// 
// 	public static Map<Pair<Pronoun,Tense>, String> mapifyConj(List<String> conj){
// 		for(String 
// 
// 	}

	
	public String getId(){    return id;  }
	
	public void setId(String id){    this.id = id;  }
}
