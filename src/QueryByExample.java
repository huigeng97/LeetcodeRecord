import BinaryTree.TreeNode;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QueryByExample {

    private HashMap<Integer, String> map = new HashMap<>();

    private static class Trie {
        static final int ASCII_SIZE = 128;

        public Trie(TrieNode root) {
            this.root = root;
        }

        public Trie() {
            this.root = new TrieNode();
        }

        static class TrieNode {
            TrieNode[] children = new TrieNode[ASCII_SIZE];
            // isLeaf is true if leaf is the end of the word,
            boolean isLeaf;
            Trie subTrie;
            Set<Integer> ids;

            TrieNode() {
                isLeaf = false;
                ids = new HashSet<>();
                for (int i = 0; i < ASCII_SIZE; i++)
                    children[i] = null;
            }

        }

        TrieNode root;

        //Insert a key into the current Trie;
        TrieNode insert(String key) {
            int length = key.length();
            int index;
            TrieNode curr = root;
            for (int i = 0; i < length; i++) {
                index = (int) key.charAt(i);
                if (curr.children[index] == null)
                    curr.children[index] = new TrieNode();
                curr = curr.children[index];
            }
            curr.isLeaf = true;
            return curr;
        }

        TrieNode search(String key) {
            int length = key.length();
            int index;
            TrieNode curr = root;
            for (int i = 0; i < length; i++) {
                index = (int) key.charAt(i);
                if (curr.children[index] == null)
                    return null;
                curr = curr.children[index];
            }
            if (curr != null && curr.isLeaf) {
                return curr;
            }
            return null;
        }
    }


    Trie dictTrie = new Trie();
    Integer id = 0;

    //addFunction parse a json String into a nest HashMap;
    public void add(String JSON) throws ParseException {
        map.put(id,JSON);
        System.out.println(JSON);
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(JSON);
        addHelper(dictTrie,jsonObj,JSON);
        System.out.println(map.size());
        id++;
    }

    private void addHelper(Trie parent, JSONObject jsonObj, String json) {
        Set<String> keys = jsonObj.keySet();
        Trie curr = parent;
        for (String key : keys) {
            // First insert the key;
            Trie.TrieNode node = parent.search(key);
            if (node == null) {
                node = parent.insert(key);
            }
            curr = node.subTrie;
            if (curr == null) {
                node.subTrie = new Trie();
                curr = node.subTrie;
            }
            // if the value is not a json insert it into the key.subtrie; //
            Object value = jsonObj.get(key);
            if (!value.getClass().equals(JSONObject.class)) {
                if (value.getClass().equals(JSONArray.class)) {
                    for (Object item : (List) value) {
                        node = curr.search(item.toString());
                        if (node == null) {
                            node = curr.insert(item.toString());
                        }
                        node.ids.add(id);
                    }
                } else {
                    node = curr.search(value.toString());
                    if (node == null) {
                        node = curr.insert(value.toString());
                    }
                    node.ids.add(id);
                }
            } else {
                // else recursive to insert into the key.subtrie;
                addHelper(curr, (JSONObject) value, json);
            }
        }
    }

    //getFunction
    public String get(String JSON) throws ParseException {
        String res = "";
        System.out.println(JSON);
        List<Integer> sortDocument = search(JSON);
        for (Integer id : sortDocument) {
            res += map.get(id) + "\n";
        }
        return res;
    }

    public List<Integer> search2(String JSON) throws ParseException {
        System.out.println("Search for " + JSON);
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(JSON);
        Set<String> keys = jsonObj.keySet();
        Set<Integer> temp = new HashSet<>(){};
        temp.addAll(map.keySet());
        // for each key searchHelper return a list of ids;
        for (String key : keys) {
            Trie.TrieNode node = searchHelper(dictTrie, key, jsonObj.get(key));
            Set<Integer> findIDs;
            if (node == null) {
                findIDs = new HashSet<>();
            } else {
                findIDs = node.ids;
            }
            Set<Integer> intersection = new HashSet<>(findIDs);
            for (Integer id : findIDs) {
                if (!map.keySet().contains(id)) {
                    node.ids.remove(id);
                }
                if (!temp.contains(id)) {
                    intersection.remove(id);
                }
            }
            temp = intersection;
        }
        List<Integer> sortDocument = asSortedList(temp);
        return sortDocument;
    }

    public Trie.TrieNode searchHelper(Trie dictTrie, String key, Object items) {
        // Search the key in the dictTrie;
        // if key exist recursive
        // search the items
            // if the item is a JSONArray
                // for each items
                    // search and intersection;
            // if the item is a JSONObject
                // recursively search the key, items
            // if the item is a String
                // search the String
        // else return null;
        return null;
    }


    public List<Integer> search(String JSON) throws ParseException {
        // next line is the initialization;
        System.out.println("Search for " + JSON);
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(JSON);
        Set<String> keys = jsonObj.keySet();
        Set<Integer> temp = new HashSet<>(){};
        temp.addAll(map.keySet());
        for (String key : keys) {
            Object value = jsonObj.get(key);
            Trie curr = dictTrie;
            Trie.TrieNode node;

            while (value.getClass().equals(JSONObject.class)) {
                // if the value is still a JSONobject, go to the nested value;
                System.out.println("Search Key : " + key);
                node = curr.search(key);
                if (node == null) {
                    break;
                }
                curr = node.subTrie;
                if (curr == null) {
                    break;
                }
                JSONObject val = (JSONObject) value;
                key = (String) val.keySet().iterator().next();
                value = val.get(key);
            }
            // now we go to the lowest level;
            System.out.println("Key : " + key);
            node = curr.search(key);
            if (node == null) {
                temp = new HashSet<>();
                break;
            }
            curr = node.subTrie;
            if (curr == null) {
                temp = new HashSet<>();
                break;
            }
            if (value.getClass().equals(JSONArray.class)) {
                for (Object item : (List) value) {
                    System.out.println("Search for item: " + item.toString());
                    node = curr.search(item.toString());
                    Set<Integer> findIDs = node.ids;
                    Set<Integer> intersection = new HashSet<>(findIDs);
                    for (Integer id : findIDs) {
                        if (!map.keySet().contains(id)) {
                            node.ids.remove(id);
                        }
                        if (!temp.contains(id)) {
                            intersection.remove(id);
                        }
                    }
                    temp = intersection;
                }
            } else {
                System.out.println("Search for value: " + value.toString());
                node = curr.search(value.toString());
                Set<Integer> findIDs = node.ids;
                Set<Integer> intersection = new HashSet<>(findIDs);
                for (Integer id : findIDs) {
                    if (!map.keySet().contains(id)) {
                        node.ids.remove(id);
                    }
                    if (!temp.contains(id)) {
                        intersection.remove(id);
                    }
                }
                temp = intersection;
            }
        }
        List<Integer> sortDocument = asSortedList(temp);
        return sortDocument;
    }

    // This method convert the Set into a sorted list;
    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }

    //deleteFunction
    public void delete(String JSON) throws ParseException {
        List<Integer> sortDocument = search(JSON);
        for (Integer id : sortDocument) {
            map.remove(id);
        }
    }

    public String parseInput(Scanner sc) throws ParseException {
        String res = "";
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            System.out.println(userInput);
            String op = userInput.split(" ")[0];
            String doc = userInput.substring(op.length()+1);
            if (op.equals("add")) {
                add(doc);
            } else if (op.equals("delete")) {
                delete(doc);
            } else if (op.equals("get")) {
                res += get(doc);
            } else {
                System.out.println("Wrong operation");
            }
        }
        return res;
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        QueryByExample instance = new QueryByExample();
        File file = new File("src/input001.txt");
        Scanner scanner = new Scanner(file);
        System.out.println(instance.parseInput(scanner));
    }
}
