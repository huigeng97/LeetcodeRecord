package LinkedList;


import java.util.HashMap;

/**
 * @author Geng Hui
 */

public class CopyListwithRandomPointet138 {
    // this problem could be done with recursive, iterative, O(N) space and O(1) space;

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node curr = dummy;
        while (head != null) {
            if (!map.containsKey(head)) {
                Node newNode = new Node(head.val);
                map.put(head,newNode);
            }
            curr.next = map.get(head);
            curr = curr.next;
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    Node newNode = new Node(head.random.val);
                    map.put(head.random,newNode);
                }
                curr.random = map.get(head.random);
            }
            head = head.next;
        }
        return dummy.next;
    }

    // try to do it with the O(1) space;
    // so if we don't have the map to record, we need to weave the new node and old node together, and then

    public Node copyRandomList2(Node head) {
        if (head == null) {return null;}
        // weave the new node with the
        Node start = head;
        while (head != null) {
            Node temp = head.next;
            head.next = new Node(head.val);
            head.next.next = temp;
            head = head.next.next;
        }
        head = start;
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        // unweave the node;
        head = start;
        Node curr = start.next;
        Node newHead = start.next;
        while (head != null) {
            head.next = curr.next;
            head = head.next;
            if (head != null) {
                curr.next = head.next;
            }
            curr = curr.next;
        }

        return newHead;
    }
}
