package BackTracking;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/// This question is a combination of the backtracking and the trie;
public class WordSearchII212 {
    static class TrieNode {
        HashMap<Character,TrieNode> children = new HashMap<>();
        String word = null;
        public TrieNode() {}
    }

    static ArrayList<String> result = new ArrayList<>();

    public static List<String> findWords(char[][] board, String[] words) {
        /// Initial the trie first;
        TrieNode root = new TrieNode();
        for (String word:words) {
            TrieNode node = root;

            for (char letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter,newNode);
                    node = newNode;
                }
            }
            node.word = word;

        }

        /// The next step is to start each position, do the backtracking;
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(board, row, col, root);
                }
            }
        }
        return result;
    }

    static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};


    // Something wrong but I am not sure where it is;....
    // The solution used the one that process with the the New root;
    private static void backtracking(char[][] board, int row, int col, TrieNode node) {
        //// check if complete // check if the row and col is valid;

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
            return;
        }

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return;

        char letter = board[row][col];

        // explore the neighbors
        TrieNode currNode = node.children.get(letter);
        if (currNode == null) return;

        board[row][col] = '#';
        for (int[] dir : dirs) {
            backtracking(board,row+dir[0],col+dir[1], currNode);
        }

        board[row][col] = letter;
        // if (currNode.children.isEmpty())  node.children.remove(letter);
    }
}




