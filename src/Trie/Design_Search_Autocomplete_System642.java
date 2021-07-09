package Trie;

import java.util.*;

public class Design_Search_Autocomplete_System642 {

  // here is the idea;
  // use the Trie;
  // in each  (TrieNode) char, we use a TreeMap (integer, String) store the whole list that with the
  // prefix of it;
  //
  class AutocompleteSystem {

    Trie trie;
    StringBuilder inputs = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
      trie = new Trie();
      for (int i = 0; i < sentences.length; i++) {
        trie.insert(sentences[i], times[i]);
      }
    }

    public List<String> input(char c) {

      if (c == '#') {
        trie.insert(new String(inputs), 1);
        inputs = new StringBuilder();
        return new ArrayList<>();
      } else {
        inputs.append(c);
        return trie.search(new String(inputs));
      }
    }

    public class Trie {

      TrieNode root = new TrieNode();

      public void insert(String sentence, int time) {
        TrieNode node = root;
        for (char ch : sentence.toCharArray()) {
          if (node.children[ch] == null) {
            node.children[ch] = new TrieNode();
          }
          node = node.children[ch];
          node.sentence.put(sentence, node.sentence.getOrDefault(sentence, 0) + time);
        }
      }

      public List<String> search(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
          if (node.children[ch] == null) {
            return new ArrayList<>();
          } else {
            node = node.children[ch];
          }
        }
        List<String> res = new ArrayList<>();
        /// now search the top three keys in the node.sentence;

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> b.c == a.c ? a.s.compareTo(b.s) : b.c - a.c);
        for (Map.Entry<String, Integer> entry : node.sentence.entrySet()) {
          pq.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
          res.add(pq.poll().s);
        }
        // use a
        return res;
      }

      class Pair {
        String s;
        int c;

        public Pair(String s, int c) {
          this.s = s;
          this.c = c;
        }
      }

        /**
         *
         */
      class TrieNode {
        TrieNode[] children = new TrieNode[128];
        HashMap<String, Integer> sentence = new HashMap<>();
      }
    }
  }

  class Trie1 {
      private Trie1() {
          // do something
          // let's go!
      }
  }
}
