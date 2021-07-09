package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

///     Please notice the difference between the 139 and 140;
//         the 139 is a True or False problem, and we don't care about how the solution make up, we only care about if
//         there exists a solution, each word could use multiple times, so in this case we use the DP,
//         However 140, we care about the solution space, therefore the best one would be the backtrack all the solutions!

public class WordBreakII140 {
    class TrieNode {
        Map<Character, TrieNode> children;
        String isWord;

        TrieNode() {
            children = new HashMap<Character, TrieNode>();
        }
    }

    TrieNode root;
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        root = new TrieNode();
        res = new ArrayList<>();
        /// add all the words into the trie;
        addWordtoDict(wordDict);
        /// backtracking the root to see if the word could be segmented;
        backtrack(s,wordDict, new ArrayList<>());
        return res;
    }

    private void backtrack(String s, List<String> wordDict, ArrayList<String> temp) {
        if (s.length() == 0) {
            res.add(String.join(" ", temp));
            return;
        }
        int i = 0;
        TrieNode node = root;
        while (i < s.length()) {
            // if the node contains the key and is Word, then recursion;
            // else return false;
            if (node.children.containsKey(s.charAt(i))) {
                node = node.children.get(s.charAt(i));
                if (node.isWord != null) {
                    temp.add(node.isWord);
                    backtrack(s.substring(i+1),wordDict, temp);
                    temp.remove(temp.size()-1);
                }
            } else {return;}
            i++;
        }
        return;
    }

    private void addWordtoDict(List<String> wordDict) {
        for (String word : wordDict) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.isWord = word;
        }
    }
}
