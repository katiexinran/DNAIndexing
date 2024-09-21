//to compile: javac BinarySearchAutocomplete.java && java BinarySearchAutocomplete
import java.util.*;

public class BinarySearchAutocomplete implements Autocomplete {
    private final List<CharSequence> terms;

    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
        Collections.sort(this.terms, CharSequence::compare);
    }

    public List<CharSequence> allMatches(CharSequence prefix){
        List<CharSequence> result = new ArrayList<>();
        int lowerBound = Collections.binarySearch(terms, prefix, CharSequence::compare);
        if(lowerBound < 0){
            lowerBound = -(lowerBound) - 1;
        }
        result = findRest(prefix, lowerBound, result);
        return result;
    }

    private List<CharSequence> findRest(CharSequence prefix, int lowerBound, List<CharSequence> result){
        for(int i = lowerBound; i < terms.size(); i++){
            if(prefix.length() <= terms.get(i).length()){
                CharSequence part = terms.get(i).subSequence(0, prefix.length());
                if(CharSequence.compare(prefix, part) == 0){
                    result.add(terms.get(i));
                }
            }
        }
        return result;
    }
}
