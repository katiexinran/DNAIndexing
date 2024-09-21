// javac TernarySearchTreeAutocomplete.java && java TernarySearchTreeAutocomplete
// javac SimpleExample.java && java SimpleExample

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TernarySearchTreeAutocomplete implements Autocomplete {
    private Node overallRoot;

    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence term : terms) {
            put(term);
        }
    }

    public void put(CharSequence key) {
        if (key == null) {
            throw new NullPointerException("calls put() with null key");
        }
        overallRoot = put(overallRoot, key, 0);
    }

    private Node put(Node x, CharSequence key, int d) {
        char c = key.charAt(d);
        boolean isTerm = (d == key.length() - 1);
        if (x == null) {
            x = new Node(c, isTerm);
            x.data = c;
        }
        if (c < x.data) {
            x.left = put(x.left, key, d);
        } else if (c > x.data) {
            x.right = put(x.right, key, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, d + 1);
        } else {
            x.isTerm = true; // Update isTerm
        }
        return x;
    }

    public Node get(CharSequence key) {
        if (key == null) {
            throw new NullPointerException("calls get() with null argument");
        } else if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        Node x = get(overallRoot, key, 0);
        return x;
    }


    private Node get(Node x, CharSequence key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.data) {
            return get(x.left, key, d);
        } else if (c > x.data) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null) {
            throw new NullPointerException("calls keysWithPrefix() with null argument");
        } else if (prefix.length() == 0) {
            throw new IllegalArgumentException("prefix must have length >= 1");
        }
        Node x = get(overallRoot, prefix, 0);
        if (x == null) {
            return result;
        }
        if (x.isTerm) {
            result.add(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), result);
        return result;
    }

    private void collect(Node x, StringBuilder prefix, List<CharSequence> list) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, list);
        if (x != null && x.isTerm) {
            list.add(prefix.toString() + x.data);
        }
        prefix.append(x.data);
        collect(x.mid, prefix, list);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, list);
    }

    private static class Node {
        public char data;
        public boolean isTerm;
        public Node left, mid, right;
        public int value;

        public Node(char data, boolean isTerm) {
            this.data = data;
            this.isTerm = isTerm;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}

