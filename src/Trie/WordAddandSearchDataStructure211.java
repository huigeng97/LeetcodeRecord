package Trie;

import java.util.HashMap;
import java.util.Map;

public class WordAddandSearchDataStructure211 {

    class WordDictionary {
        class TrieNode {
            Map<Character, TrieNode> children;
            String isWord;

            TrieNode() {
                children = new HashMap<Character, TrieNode>();
            }
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.isWord = word;
        }

        public boolean search(String word) {
            return  search(word,root);
        }

        private boolean search(String word, TrieNode node) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch == '.') {
                    for (TrieNode curr : node.children.values()) {
                        if (search(word.substring(i+1),curr)) return true;
                    }
                    return false;
                } else if (node.children.containsKey(ch)) {
                    node = node.children.get(ch);
                } else return false;
            }
            return node.isWord != null;
        }
    }
}
