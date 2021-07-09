package LinkedList;

public class Palindrome_Linked_List234 {

    // simple solution like restore into an Array and then use the two pointer to check it; but with O(n) SPACE,
    // others with the O(1) SPACE would be relatd with the core of the linkedList;
    // we could seperate the list by two part;
    // reverse the second part
    // check it
    // reverse the second part again the connect with firstpartEnd;
        public boolean isPalindrome(ListNode head) {
            if (head.next == null) return true;
            // we could seperate the list by two part;
            // reverse the second part
            // check it
            // reverse the second part again the connect with firstpartEnd;
            ListNode firstpartEnd = splitLinkedList(head);
            ListNode secondPartFirst = firstpartEnd.next;
            firstpartEnd.next = null;
            secondPartFirst = reverse(secondPartFirst);

            boolean res = isSame(head, secondPartFirst);
            secondPartFirst = reverse(secondPartFirst);
            firstpartEnd.next = secondPartFirst;
            return res;
        }

        // return the end of the first part;
        private ListNode splitLinkedList(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next!= null && fast.next.next!= null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }


        // Use the O(1) space to reverse it;
        // 逻辑！逻辑！
        private ListNode reverse(ListNode head) {
            ListNode front = head.next;
            head.next = null;
            while (front != null) {
                ListNode temp = front.next;
                front.next = head;
                head = front;
                front = temp;
            }
            return head;
        }


        private boolean isSame(ListNode l1, ListNode l2) {
            while (l2 != null) {
                if (l1.val != l2.val) return false;
                l2 = l2.next;
                l1 = l1.next;
            }
            return true;
        }

}
