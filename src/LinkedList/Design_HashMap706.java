package LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Design_HashMap706 {

    class MyHashMap {


        List<Integer[]>[] data;
        int bucket = 1000;
        int size;
        /** Initialize your data structure here. */
        public MyHashMap() {
            data = new LinkedList[bucket];
            size = 0;
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hash = hash(key);
            for (Integer[] pair : data[hash]) {
                if (pair[0] == key) {
                    pair[1] = value;
                    return;
                }
            }
            data[hash].add(new Integer[]{key,value});
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int value = hash(key);
            for (Integer[] pair : data[value]) {
                if (pair[0] == key) return pair[1];
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hash = hash(key);
            List<Integer[]> temp = data[hash];
            for (Integer[] pair : temp) {
                if (pair[0] == key) {
                    temp.remove(pair);
                    return;
                };
            }
        }

        public int hash(int key) {
            return key * 13 % bucket;
        }
    }

    class MyHashMap2 {
        final ListNode[] nodes = new ListNode[10000];

        public void put(int key, int value) {
            int i = idx(key);
            if (nodes[i] == null)
                nodes[i] = new ListNode(-1, -1);
            ListNode prev = find(nodes[i], key);
            if (prev.next == null)
                prev.next = new ListNode(key, value);
            else prev.next.val = value;
        }

        public int get(int key) {
            int i = idx(key);
            if (nodes[i] == null)
                return -1;
            ListNode node = find(nodes[i], key);
            return node.next == null ? -1 : node.next.val;
        }

        public void remove(int key) {
            int i = idx(key);
            if (nodes[i] == null) return;
            ListNode prev = find(nodes[i], key);
            if (prev.next == null) return;
            prev.next = prev.next.next;
        }

        int idx(int key) { return Integer.hashCode(key) % nodes.length;}

        ListNode find(ListNode bucket, int key) {
            ListNode node = bucket, prev = null;
            while (node != null && node.key != key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }

        class ListNode {
            int key, val;
            ListNode next;

            ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }


    class MyHashMap3 {

        int bucket = 1000;
        final ListNode[] nodes;
        /** Initialize your data structure here. */
        public MyHashMap3() {
            nodes = new ListNode[bucket];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int i = idx(key);
            if (nodes[i] == null)
                nodes[i] = new ListNode(-1, -1);
            ListNode prev = find(key);
            if (prev.next == null)
                prev.next = new ListNode(key, value);
            else prev.next.val = value;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            ListNode prev = find(key);
            if (prev == null) return -1;
            else return prev.next == null ? -1 : prev.next.val;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            ListNode prev = find(key);
            if (prev == null) return;
            if (prev.next != null) prev.next = prev.next.next;
        }

        public ListNode find(int key) {
            int idx = idx(key);
            ListNode prev = null;
            ListNode node = nodes[idx];
            while (node != null && node.key == key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }

        private int idx(int key) {
            return Integer.hashCode(key) % bucket;
        }

        class ListNode {
            int val;
            int key;
            ListNode next;

            public ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

}
