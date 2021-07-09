package LinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class Test146 {
    class LRUCache {


        class Dnode {
            Dnode prev;
            Dnode next;
            int value;
            int key;

            public Dnode(int val, int k) {
                value = val;
                key = k;
            }

            public Dnode() {

            }
        }
        Map<Integer, Dnode> cache = new HashMap<>();
        int capacity;
        int size = 0;
        Dnode head;
        Dnode tail;


        public LRUCache(int capacity) {

            this.capacity = capacity;
            head = new Dnode();
            tail = new Dnode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {

            Dnode node = cache.get(key);
            if(node == null){
                return -1; // should raise exception here.
            }

            // move the accessed node to the head;
            moveToTail(node);

            return node.value;
        }

        // move a node from the middle to the tail;
        private void moveToTail(Dnode curr) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            addTail(curr);
        }

        public void put(int key, int value) {

            // if map contains key, just get and moveToTail and update the value;
            if (cache.containsKey(key)) {
                Dnode curr = cache.get(key);
                moveToTail(curr);
                curr.value = value;
            }

            // else we need to if the size is larger than the capacity;
            // if exceed, we need to popthehead;
            // add the tail;

            else {
                size ++;
                if (size > capacity) {
                    popHead();
                }
                Dnode newNode = new Dnode(value,key);
                addTail(newNode);
                cache.put(key,newNode);

            }
        }

        private void addTail(Dnode curr) {
            tail.prev.next = curr;
            curr.prev = tail.prev;
            curr.next = tail;
            tail.prev = curr;
        }

        private void popHead() {
            Dnode delete = head.next;
            cache.remove(delete.key);
            head.next = delete.next;
            head.next.prev = head;
        }
    }
}
