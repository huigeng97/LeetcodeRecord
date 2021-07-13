package Trie;

import java.util.*;

public class Design_InMemory_File_System588 {

    class FileSystem2 {

        class Trie {
            TrieNode root = new TrieNode();
            class TrieNode {
                TrieNode[] children = new TrieNode[26];
                boolean isFile = false;
                HashSet<String> subDir = null;
                String file = null;

                public TrieNode addFolder(String word) {
                    TrieNode curr = this;

                    if (!curr.subDir.contains(word)) {
                        curr.subDir.add(word);
                    }
                    for (char ch : word.toCharArray()) {
                        if (curr.children[ch-'a'] == null) {
                            curr.children[ch-'a'] = new TrieNode();
                        }
                        curr = curr.children[ch-'a'];
                    }
                    if (curr.subDir == null) {
                        curr.subDir = new HashSet<>();
                    }
                    return curr;
                }
            }
        }
        Trie fileSystem;
        public FileSystem2() {
            fileSystem = new Trie();
            fileSystem.root.subDir = new HashSet<>();
        }

        public List<String> ls(String path) {
            if (path.equals("/")) {
                List res = new ArrayList(fileSystem.root.subDir);
                Collections.sort(res);
                return res;
            }
            String[] dicts = path.split("/");
            Trie.TrieNode curr = fileSystem.root;
            for (int i = 1; i < dicts.length; i++) {
                System.out.println("curr ls : " + dicts[i]);
                curr = curr.addFolder(dicts[i]);
            }
            List res = new ArrayList(curr.subDir);
            Collections.sort(res);
            return res;
        }

        public void mkdir(String path) {
            if (path.equals("/")) { return;}
            String[] dicts = path.split("/");
            Trie.TrieNode curr = fileSystem.root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {

            if (filePath.equals("/")) { return;}
            String[] dicts = filePath.split("/");
            Trie.TrieNode curr = fileSystem.root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }

            if (curr.isFile) {
                curr.file += content;
            } else {
                curr.subDir.add(dicts[dicts.length - 1]);
                curr.isFile = true;
                curr.file = content;
            }
        }

        public String readContentFromFile(String filePath) {
            if (filePath.equals("/")) {
                if (fileSystem.root.isFile) {
                    return fileSystem.root.file;
                } else {
                    return null;
                }
            }
            String[] dicts = filePath.split("/");
            Trie.TrieNode curr = fileSystem.root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }
            if (curr.isFile) {
                return curr.file;
            } else {
                return null;
            }
        }
    }

    class FileSystem {


        class FileNode {
            TreeMap<String, FileNode> children = new TreeMap();
            boolean isFile = false;
            HashSet<String> subDir = null;
            String file = null;

            public FileNode addFolder(String word) {
                FileNode curr = this;
                if (!curr.subDir.contains(word)) {
                    curr.subDir.add(word);
                }
                curr = curr.children.getOrDefault(word, new FileNode());
                if (curr.subDir == null) {
                    curr.subDir = new HashSet<>();
                }
                return curr;
            }
        }
        FileNode root;
        public FileSystem() {
             root = new FileNode();
             root.subDir = new HashSet<>();
        }

        public List<String> ls(String path) {
            if (path.equals("/")) {
                List res = new ArrayList(root.subDir);
                Collections.sort(res);
                return res;
            }
            String[] dicts = path.split("/");
            FileNode curr = root;
            for (int i = 1; i < dicts.length; i++) {
                System.out.println("curr ls : " + dicts[i]);
                curr = curr.addFolder(dicts[i]);
            }
            List res = new ArrayList(curr.subDir);
            Collections.sort(res);
            return res;
        }

        public void mkdir(String path) {
            if (path.equals("/")) { return;}
            String[] dicts = path.split("/");
            FileNode curr = root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {

            if (filePath.equals("/")) { return;}
            String[] dicts = filePath.split("/");
            FileNode curr = root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }

            if (curr.isFile) {
                curr.file += content;
            } else {
                curr.subDir.add(dicts[dicts.length - 1]);
                curr.isFile = true;
                curr.file = content;
            }
        }

        public String readContentFromFile(String filePath) {
            if (filePath.equals("/")) {
                if (root.isFile) {
                    return root.file;
                } else {
                    return null;
                }
            }
            String[] dicts = filePath.split("/");
            FileNode curr = root;
            for (int i = 1; i < dicts.length; i++) {
                curr = curr.addFolder(dicts[i]);
            }
            if (curr.isFile) {
                return curr.file;
            } else {
                return null;
            }
        }
    }
}
