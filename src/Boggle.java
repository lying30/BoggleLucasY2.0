import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;

        public void insert (String word){

        }
        public boolean startsWith(String prefix) {
            return true;
        }
        public boolean search(String word){
            return true;
        }
    }

    public static String[] findWords(char[][] board, String[] dictionary) {

       TrieNode trie = new TrieNode();
       for (String word: dictionary) {
           trie.insert(word);
       }
       
       ArrayList<String> goodWords = new ArrayList<String>();
       boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                explore(board, i, j, visited, trie, "", goodWords);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
    public static void explore(char[][] board, int x, int y, boolean[][] visited, TrieNode trie, String word, ArrayList<String> goodWords){
        if ()
    }
}
