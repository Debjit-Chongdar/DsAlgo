package trie;

import java.util.*;

public class WordSearch2DArray {
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endWord;
        public TrieNode(){
            this.children = new HashMap();
        }
        //for all cases add word will start from root
        public void addWord(String word){
            TrieNode cur = this;
            for(char ch : word.toCharArray()){
                cur.children.putIfAbsent(ch, new TrieNode());
                cur = cur.children.get(ch);
            }
            cur.endWord=true;
        }
    }
    public List<String> matchWords(char[][] board, String[] words){
        List<String> result = new ArrayList();
        TrieNode root = new TrieNode();
        //create a Trie map Tree for all words
        for(String word : words){
            root.addWord(word);
        }
        //it will be required during depth first search to evaluate if some cell on the board is already visited
        boolean[][] visited=new boolean[board.length][board[0].length];
        //search for the character start from any cell is the starting point of the Trie tree root
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                //if the char is present in the Trie tree root nodes
                if(root.children.containsKey(board[row][col])){
                    addIfMatch(root.children.get(board[row][col]), board, visited, row, col, result, "");
                }
            }
        }
        return result;
    }
    //DFS search to find the same string as Trie Tree present in the board
    private void addIfMatch(TrieNode root, char[][] board, boolean[][] visited, int row, int col, List<String> result, String word){
        //as it's a match, so we visited this cell
        visited[row][col]=true;
        word=word+board[row][col]; // add the character
        if(root.endWord){
            result.add(word); //if it's end of any trie tree then it's a match like (back,backend)
        }//don't return as it's cane have more children like back and backend
        Set<Character> keyset = root.children.keySet();
        if(!keyset.isEmpty()){
            //if next character present in the trie children
            if(row>0 && !visited[row-1][col] && keyset.contains(board[row-1][col])){
                addIfMatch(root.children.get(board[row-1][col]), board, visited, row-1, col, result, word);
            }
            if(row<board.length-1 && !visited[row+1][col] && keyset.contains(board[row+1][col])){
                addIfMatch(root.children.get(board[row+1][col]), board, visited, row+1, col, result, word);
            }
            if(col>0 && !visited[row][col-1] && keyset.contains(board[row][col-1])){
                addIfMatch(root.children.get(board[row][col-1]), board, visited, row, col-1, result, word);
            }
            if(col<board[0].length-1 && !visited[row][col+1] && keyset.contains(board[row][col+1])){
                addIfMatch(root.children.get(board[row][col+1]), board, visited, row, col+1, result, word);
            }
        }else{
            return;
        }
        visited[row][col]=false; //revert back the changes as it will be re calculated from other position of the cell
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a','b','c','d'},
                {'s','a','a','t'},
                {'a','c','k','e'},
                {'a','c','d','n'}
        };
        String[] words = {"bat","cat","back","backend", "stack"};
        List<String> output = new WordSearch2DArray().matchWords(board, words);
        System.out.println("Expected = [cat, back, backend]");
        System.out.println("Actuals = "+ output.toString());
    }
}
//            root
//          /   |   \
//      b       c       s
//      |       |       |
//      a       a       t
//     / \      |       |
//   (t)  c    (t)      a
//        |             |
//       (k)            c
//        |             |
//        e            (k)
//        |
//        n
//        |
//       (d)