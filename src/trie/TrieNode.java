package trie;

public class TrieNode {
    public TrieNode[] children;
    public boolean endOfWord;
    public TrieNode(){
        children = new TrieNode[26]; //a-z has 26 character
        endOfWord = false;
    }
}
//           root
//         /  |  \
//        d   b   m
//       /    |    \
//      a     a     a
//     /      |      \
//   (y)     (y)     (y)

//           root
//         /  |  \
//        3   1   12
//       /    |    \
//      0     0     0
//     /      |      \
//   (24)    (24)    (24)