package LinkedList;

import java.util.List;

public class Reverse_Nodes_in_k_Group25 {

    // if k is larger than the head, return;
    // I would reverse the first k and then use a recursion to reverse the others;
    // reverse the node K times;
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) return head;
            curr = curr.next;
        }
        ListNode reverseHead = reverse(head,k);
        head.next = reverseKGroup(curr,k);
        return reverseHead;
    }

    private ListNode reverse(ListNode head, int k) {
        // return two nodes;


        // Starting from the null nodes, so that we could count each reverse and compare with  the K;
        ListNode newHead = null;
        while (k>0) {
            ListNode temp2 = head.next;
            head.next = newHead;
            newHead = head;
            head = temp2;
            k--;
        }
        return newHead;
    }










    public static void main(String[] args) {
        Reverse_Nodes_in_k_Group25 test = new Reverse_Nodes_in_k_Group25();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        test.reverseKGroup(head, 2);
    }
}
