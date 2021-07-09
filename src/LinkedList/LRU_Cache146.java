package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRU_Cache146 {

    // 来日再战！
    // 对于这种比较大的problem,
    // when implementation, we need to come up with some helper methods instead of just coding directly,
    // which could make the problem much more clear;

    // 保证在能够implement成功基础上在考虑适当简化，明明用double linkedlist 更简单，就不要用singly list;
    static class LRUCache {

        // use the hashmap and linkedlist to do it;
        // in each of the hashMap, we store a linkedlist address for the prev pointer;
        // for the linkedlist we store the head and tail;
        ListNode head;
        ListNode tail;
        Map<Integer, ListNode> map;
        int size = 0;
        int capacity;
        public LRUCache(int capacity) {
            head = new ListNode();
            tail = head;
            this.capacity = capacity;
            map = new HashMap<Integer, ListNode>();
        }

        public class ListNode {
            public int key;
            public int val;
            public ListNode next;
            public ListNode() {}
            public ListNode(int key, int val) { this.val = val; this.key = key;}
            public ListNode(int val, ListNode next) { this.val = val; this.next = next; };
        }

        public int get(int key) {
            System.out.println("get : " + key );
            ListNode prev = getHelper(key);
            if (prev == null) return -1;
            else return prev.next.val;
        }

        public ListNode getHelper(int key) {
            if (!map.containsKey(key)) return null;
            // ListNode prev = map.get(key);
            // head.next = prev.next;
            // prev.next = prev.next.next;
            // map.put(prev.next.val,prev);
            // prev = head;
            // head = head.next;
            // System.out.println("change : " + key + " parents as the  " + prev.val);
            // map.put(key,prev);
            ListNode prev = map.get(key);
            ListNode node = prev.next;
            head.next = node;
            prev.next = prev.next.next;
            map.put(prev.next.key, prev);
            prev = head;
            map.put(node.key,head);
            head = head.next;

            return prev;
        }

        public void put(int key, int value) {
            System.out.println("put : " + key );
            ListNode prev = getHelper(key);
            // we put an old item;
            if (prev != null) {
                System.out.println("we put an old item;" + key);
                prev.next.val = value;
                // we put a new item;
            } else {
                head.next = new ListNode(key,value);
                System.out.println("put : " + key + " parents as the  " + head.val);
                map.put(key,head);
                head = head.next;
                size++;
                if (size > capacity) {
                    System.out.println("remove : " + tail.next.key + " when inserting the " + key);
                    map.remove(tail.next.key);
                    tail = tail.next;
                    System.out.println("tail now is  : " + tail.key);
                    size--;
                }
            }
        }

    }
    public static void main(String[] args) {
        LRUCache test = new LRUCache(2);
        test.put(2,1);
        test.put(2,2);
        test.get(2);
        test.put(1,1);
        test.put(4,1);
        test.get(2);


    }

}
