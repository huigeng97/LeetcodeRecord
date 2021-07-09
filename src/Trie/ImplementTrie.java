package Trie;

public class ImplementTrie {

    // The search and startWith has the pretty similar function,
    // Use one common helper function! instead of using two in this problem!
    class Trie {

        /** Initialize your data structure here. */
        class TrieNode {
            TrieNode[] children;
            boolean isWord;
            boolean hasChild;

            TrieNode () {
                children = new TrieNode[26];
            }
        }
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch-'a'] == null) {
                    node.children[ch-'a'] = new TrieNode();
                    node.hasChild = true;
                }
                node = node.children[ch-'a'];
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
                if (node.children[ch-'a'] == null) {
                    return null;
                } else {
                    node = node.children[ch-'a'];
                }
            }
            return node;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }
}
