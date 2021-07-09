package Trie;

import java.util.Arrays;

// There are some other methods like  set and sort first then check the substring each time;
// Others like adding the words to the trie and use the DFS to traverse the trie;
public class Longest_Word_in_Dictionary720 {
  public static void main(String[] args) {
    Longest_Word_in_Dictionary720 test = new Longest_Word_in_Dictionary720();
    test.longestWord(new String[] {"a", "banana", "app", "appl", "ap", "apply", "apple"});
  }

  public String longestWord(String[] words) {
    Arrays.sort(
        words, (a, b) -> a.length() == b.length() ? b.compareTo(a) : a.length() - b.length());
    Trie trie = new Trie();
    String res = "";
    for (String word : words) {
      if (trie.insert(word)) {
        res = word;
      }
    }
    return res;
  }

  class Trie {
    TrieNode root;

    Trie() {
      root = new TrieNode();
    }

    public boolean insert(String word) {
      TrieNode node = root;
      for (int i = 0; i < word.length() - 1; i++) {
        if (node.children[word.charAt(i) - 'a'] != null) {
          node = node.children[word.charAt(i) - 'a'];
        } else {
          return false;
        }
        ;
      }
      node.children[word.charAt(word.length() - 1) - 'a'] = new TrieNode();
      return true;
    }

    class TrieNode {
      TrieNode[] children;

      TrieNode() {
        children = new TrieNode[26];
      }
    }
  }
}
