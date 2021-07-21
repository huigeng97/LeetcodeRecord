package DFS;

public class DesignAddandSearchWordsDataStructure211 {

    class WordDictionary {

        /** Initialize your data structure here. */
        // use a trie to search;

        TrieNode root = new TrieNode();

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isWord = false;
        }

        public WordDictionary() {
        }

        public void addWord(String word) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.isWord = true;
        }

        public boolean search(String word) {
            return searchHelper(word, root);
        }


        // this could also be solved by recursion to avoid the duplicate checking on the null value;

        public boolean searchHelper(String word, TrieNode curr) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch == '.') {
                    // find all the 26 children and return if one of them is true;
                    for (int j = 0; j < 26; j++) {
                        if (curr.children[j] != null) {
                            if (searchHelper(word.substring(i + 1), curr.children[j])) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else {
                    if (curr.children[ch - 'a'] == null) {
                        return false;
                    } else {
                        curr = curr.children[ch - 'a'];
                    }
                }
            }
            return curr.isWord;
        }
    }
}
