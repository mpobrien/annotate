package annotate.lang;
import java.util.regex.*;

public class SpanishStemmer{

//     public static Pattern verbPronounMatcher = Pattern.compile("\\w+(iéndo|ándo|ár|ér|ír|ando|iendo|ar|er|ir|uyendo)(me|se|sela|selo|selas|selos|la|le|lo|las|les|los|nos)");
//     public static Pattern nounEndings = Pattern.compile("\\w+(anza|anzas|ico|ica|icos|icas|ismo|ismos|amiento|amientos|imiento|imientos|ución|uciones|logía|logías|adora|ador|ación|adoras|adores|aciones|ante|antes|ancia|ancias|encia|encias|idad|idades|iva|ivo|ivas|ivos)
//     )$");
    

    public static String normalize(String input){
        return input.replaceAll("\\W","");
    }

//     public PartOfSpeech guessPartOfSpeech(String word){
//         return null;
// // 
// //         public enum PartOfSpeech{
// //             ADJECTIVE,
// //             VERB,
// //             NOUN,
// //             ADVERB,
// //             PRONOUN,
// //             INTERJECTION,
// //             CONJUNCTION
// //         };
// // 
// //         if( verbPronounMatcher.matches(word) ){
// //             return PartOfSpeech.VERB;
// //         }
//     }


}
