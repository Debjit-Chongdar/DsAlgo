package trie;

import java.util.HashMap;
import java.util.Map;

class TrieNodeMap { // using map
    public Map<Character, TrieNodeMap> children;
    public boolean endOfString;

    public TrieNodeMap() {
        children = new HashMap<>();
        endOfString = false;
    }
}

//           root
//         /  |  \
//        d   b   m
//       /    |    \
//      a     a     a
//     /      |      \
//   (y)     (y)     (y)