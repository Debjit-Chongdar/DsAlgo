package trie;

import java.util.HashMap;
import java.util.Map;

class TrieNodeMap{ // using map
    public Map<Character, TrieNodeMap> children;
    public boolean endOfString;
    public TrieNodeMap(){
        children = new HashMap<>();
        endOfString = false;
    }
}
public class Trie_PrefixTree {
    private TrieNode root; //using array
    private TrieNodeMap root_map; // using map

    public Trie_PrefixTree(){
        root = new TrieNode();
        root_map = new TrieNodeMap();
    }
    //if any character is not present then add it in the children section
    public void addWord(String word){
        TrieNode tmp = root;
        for (char ch : word.toCharArray()){
            if(tmp.children[ch-'a'] == null){
                tmp.children[ch - 'a'] = new TrieNode();
            }
            tmp = tmp.children[ch - 'a'];
        }
        tmp.endOfWord = true;
    }
    //using map
    public void addWord_map(String word){
        TrieNodeMap tmp = root_map;
        for(char ch : word.toCharArray()){
            tmp.children.putIfAbsent(ch, new TrieNodeMap());
            tmp = tmp.children.get(ch);
        }
        tmp.endOfString = true;
    }

    public boolean isPresent(String word){
        TrieNode tmp = root;
        for(char ch : word.toCharArray()){
            if(tmp.children[ch - 'a'] == null){
                return false;
            }
            tmp = tmp.children[ch - 'a'];
        }
        return tmp.endOfWord;
    }
    public boolean isPresent_map(String word){
        TrieNodeMap tmp = root_map;
        for(char ch: word.toCharArray()){
            if(!tmp.children.containsKey(ch)){
                return false;
            }
            tmp = tmp.children.get(ch);
        }
        return tmp.endOfString;
    }

    public boolean isStartingWith(String word){
        TrieNode tmp = root;
        for(char ch : word.toCharArray()){
            if(tmp.children[ch - 'a'] == null){
                return false;
            }
            tmp = tmp.children[ch - 'a'];
        }
        return true;
    }
    public boolean isStartingWith_map(String word) {
        TrieNodeMap tmp = root_map;
        for(char ch : word.toCharArray()){
            if(!tmp.children.containsKey(ch)){
                return false;
            }
            tmp = tmp.children.get(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie_PrefixTree tree = new Trie_PrefixTree();
        tree.addWord("dog");
        tree.addWord_map("dog");
        System.out.println("Expected : false # Actual : " + tree.isPresent("do") +" = : "+tree.isPresent_map("do"));
        System.out.println("Expected : true # Actual : " + tree.isStartingWith("do")+" = : "+tree.isStartingWith_map("do"));
        tree.addWord("do");
        tree.addWord_map("do");
        System.out.println("Expected : true # Actual : " + tree.isPresent("do")+" = : "+tree.isPresent_map("do"));
        System.out.println("Expected : true # Actual : " + tree.isPresent("dog")+" = : "+tree.isPresent_map("dog"));
        System.out.println("Expected : false # Actual : " + tree.isPresent("d")+" = : "+tree.isPresent_map("d"));
        System.out.println("Expected : false # Actual : " + tree.isPresent("dogs")+" = : "+tree.isPresent_map("dogs"));
    }
}
