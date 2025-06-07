package trie;

public class AddSearchWord {
    private TrieNode root;

    public AddSearchWord(){
        root = new TrieNode();
    }
    //insert is just find the position and if exist then return that node else create and assign a node in it and return
    public void insert(String word){
        TrieNode tmp = root;
        for(char ch : word.toCharArray()){
            if(tmp.children[ch - 'a'] == null){
                tmp.children[ch - 'a'] = new TrieNode();
            }
            tmp = tmp.children[ch -'a'];
        }
        tmp.endOfWord = true;
    }
    //search if this word is present in the Trie tree or not
    public boolean search(String word){
        return dfs(word, 0, root);
    }
    private boolean dfs(String word, int index, TrieNode trieNode){
        if(index < word.length()) {
            //if current posion element is . means it can be any valid children
            if (word.charAt(index) == '.') {
                for (TrieNode node : trieNode.children) {
                    //if any children satisfy the condition return true
                    if (node != null && dfs(word, index + 1, node)) {
                        return true;
                    }
                }
                //return false after all possible children fails
                return false;
            } else {
                //if it's a character then it should be a perfect match
                if (trieNode.children[word.charAt(index) - 'a'] != null &&
                        dfs(word, index + 1, trieNode.children[word.charAt(index) - 'a'])) {
                    return true;
                }
                return false;
            }
        }else{
            return trieNode.endOfWord;
        }
    }

    public static void main(String[] args) {
        AddSearchWord obj = new AddSearchWord();
        obj.insert("day");
        obj.insert("bay");
        obj.insert("may");
        System.out.println("false => "+obj.search("say"));
        System.out.println("true => "+obj.search("day"));
        System.out.println("true => "+obj.search(".ay"));
        System.out.println("true => "+obj.search("b.."));
        System.out.println("true => "+obj.search("m.y"));
        System.out.println("false => "+obj.search("may."));
        System.out.println("false => "+obj.search("s.y"));
    }
}
