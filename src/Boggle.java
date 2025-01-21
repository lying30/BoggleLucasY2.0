import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static class TrieNode {
        // Array of child nodes for each letter of the alphabet
        TrieNode[] children = new TrieNode[26];
        // Indicates whether the node marks the end of a valid word
        boolean isEndOfWord = false;

        public TrieNode() {
            // Initialize all children as null
            Arrays.fill(children, null);
        }

        // Inserts a word into the trie
        public void insert (String word){
            TrieNode current = this;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }
            current.isEndOfWord = true;
        }

        // Checks if there is any word in the trie that starts with the given prefix
        public boolean startsWith(String prefix) {
            TrieNode current = this;
            for (char c : prefix.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    // If prefix doesn't exist return false
                    return false;
                }
                current = current.children[c - 'a'];
            }
            return true;
        }

        // Searches for a specific word in the trie
        public boolean search(String word){
            TrieNode current = this;
            for (char c: word.toCharArray()) {
                if (current.children[c- 'a'] == null) {
                    // Return false if the word does not exist
                    return false;
                }
                current = current.children[c - 'a'];
            }
            // Return true if it's an exact word match
            return current.isEndOfWord;
        }
    }

    // Finds all valid words on the Boggle board that are in the given dictionary
    public static String[] findWords(char[][] board, String[] dictionary) {

       TrieNode trie = new TrieNode();
       for (String word: dictionary) {
           // Populate the true with words from the dictionary
           trie.insert(word);
       }
       
       ArrayList<String> goodWords = new ArrayList<String>();
       boolean[][] visited = new boolean[board.length][board[0].length];

       // Explore every cell on the board
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

    // Recursive method to explore all words starting from the cell (x, y)
    public static void explore(char[][] board, int x, int y, boolean[][] visited, TrieNode trie, String word, ArrayList<String> goodWords){
        // Ensure the indices are within bounds and the cell is not visited
        if (x<0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]){
            return;
        }

        // Append the current character to the word
        word += board[x][y];

        if (!trie.startsWith(word)){
            return;
        }

        if (trie.search(word) && !goodWords.contains(word)){
            // Add if valid word and not already found
            goodWords.add(word);
        }

        visited[x][y] = true;
        // Explore adjacent cells
        explore(board, x + 1, y, visited, trie, word, goodWords);
        explore(board, x - 1, y, visited, trie, word, goodWords);
        explore(board, x, y + 1, visited, trie, word, goodWords);
        explore(board, x, y - 1, visited, trie, word, goodWords);
        // Unmark this square for backtracking
        visited[x][y] = false;

    }
}
