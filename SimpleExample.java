// javac SimpleExample.java && java SimpleExample
import java.util.*;

// Run the simple example given in the specification.
public class SimpleExample {
    public static void main(String[] args) {
        List<CharSequence> terms = new ArrayList<>();
        terms.add("alpha");
        terms.add("delta");
        terms.add("do");
        terms.add("cats");
        terms.add("dodgy");
        terms.add("pilot");
        terms.add("dog");

        // Change the Autocomplete implementation.
        Autocomplete autocomplete = new TernarySearchTreeAutocomplete();
        autocomplete.addAll(terms);

        CharSequence prefix = "d";
        List<CharSequence> matches = autocomplete.allMatches(prefix);

        for (CharSequence match : matches) {
            System.out.println(match);
        }
    }
}
