//java visualizer: https://cscircles.cemc.uwaterloo.ca/java_visualize/?fbclid=IwAR13dFdxSuy_th-YHYvXQd6GSXaCKwnqJdH6So3B2i2O89UAQHLfMNHVbY4#
// javac LinearSearchAutocomplete.java && java LinearSearchAutocomplete
import java.util.*;

public class LinearSearchAutocomplete implements Autocomplete {
    private final List<CharSequence> terms;

    public LinearSearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms); 
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();

        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        
        for (int i = 0; i < this.terms.size(); i++) { 
            if(prefix.length() <= terms.get(i).length()){
                CharSequence part = terms.get(i).subSequence(0, prefix.length());
                if (CharSequence.compare(prefix, part) == 0) {
                    result.add(terms.get(i));
                }
            }
        }
        return result;
    }
}
