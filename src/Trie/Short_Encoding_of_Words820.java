package Trie;

import java.util.Arrays;

public class Short_Encoding_of_Words820 {
  public int minimumLengthEncoding(String[] words) {
    Arrays.sort(words, (a, b) -> b.length() - a.length());
    Trie trie = new Trie();
    int res = 0;
    for (String word : words) {
      StringBuilder sb = new StringBuilder();
      sb.append(word);
      sb.reverse();
      String newWord = new String(sb);
      if (trie.startsWith(newWord)) {
        continue;
      } else {
        trie.insert(newWord);
        res += word.length() + 1;
      }
    }
    return res;
  }

  // Another solution is to memorize the times of the leaf nodes that be encounter
  // If the leaf nodes only counts once, we sum them up;
  class Trie {

    TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      TrieNode node = root;
      for (char ch : word.toCharArray()) {
        if (node.children[ch - 'a'] == null) {
          node.children[ch - 'a'] = new TrieNode();
          node.hasChild = true;
        }
        node = node.children[ch - 'a'];
      }
      node.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
      TrieNode node = searchPrefix(word);
      return node != null && node.isWord;
    }

    ///
    private TrieNode searchPrefix(String word) {
      TrieNode node = root;
      for (char ch : word.toCharArray()) {
        if (node.children[ch - 'a'] == null) {
          return null;
        } else {
          node = node.children[ch - 'a'];
        }
      }
      return node;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
      TrieNode node = searchPrefix(prefix);
      return node != null;
    }

    /** Initialize your data structure here. */
    class TrieNode {
      TrieNode[] children;
      boolean isWord;
      boolean hasChild;

      TrieNode() {
        children = new TrieNode[26];
      }
    }
  }
}
