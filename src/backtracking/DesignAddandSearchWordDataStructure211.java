package backtracking;

public class DesignAddandSearchWordDataStructure211 {

    class WordDictionary {


        /** Initialize your data structure here. */
        class TrieNode {
            TrieNode[] children  = new TrieNode[26];
            boolean word = false;
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch-'a'] == null) {
                    node.children[ch-'a'] = new TrieNode();
                }
                node = node.children[ch-'a'];
            }
            node.word = true;
        }

        /// A slightly improved method would change the word into a Chararry, which will make it little bit faster;
        /// if we used the char array we need another var index to mark the starting place for the recursion;
        public boolean search(String word) {
            return searchHelper(word,root);
        }

        private boolean searchHelper(String word, TrieNode node) {
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (ch == '.') {
                    // search in the next part;
                    for (int k = 0 ; k < 26; k++) {
                        if (node.children[k]!= null) {
                            if (searchHelper(word.substring(i + 1),node.children[k])) return true;
                        }
                    }
                    return false;
                } else {
                    if (node.children[ch-'a'] == null) return false;
                    else node = node.children[ch-'a'];
                }
            }
            return node.word;
        }
    }



}
