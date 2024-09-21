import java.util.*;

public class TreeSetAutocomplete implements Autocomplete {
    private final NavigableSet<CharSequence> terms;

    public TreeSetAutocomplete() {
        this.terms = new TreeSet<>(CharSequence::compare);
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
    }

    // TreeSet utilizes LLRB, self balancing. Height at most log(n)

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        CharSequence start = terms.ceiling(prefix);
        if (start == null) {
            return result;
        }
        for (CharSequence term : terms.tailSet(start)) {
            CharSequence part = term.subSequence(0, prefix.length());
            if (CharSequence.compare(prefix, part) == 0) {
                result.add(term);
            } else {
                return result;
            }
        }
        return result;
    }
}
