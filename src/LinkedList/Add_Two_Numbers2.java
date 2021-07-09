package LinkedList;

public class Add_Two_Numbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = l1;
        ListNode r = l2;
        while (l != null && r != null) {
            if (l.next == null && r.next != null) {
                // Let the shorter number in the second place;
                return addTwoNumbers(l2, l1);
            }
            l = l.next;
            r = r.next;
        }
        // l1 is larger, l2 is shorter;
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        int carrier = 0;
        while (l2 != null) {
            int num = carrier + l1.val + l2.val;
            carrier = num / 10;
            int val = num % 10;
            res.next = new ListNode(val);
            res = res.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        // check if l1 is empty and if counter if 1;
        while (l1 != null) {
            int num = carrier + l1.val;
            carrier = num / 10;
            int val = num % 10;
            res.next = new ListNode(val);
            res = res.next;
            l1 = l1.next;
        }

        if (carrier != 0) {
            res.next = new ListNode(1);
        }
        return dummy.next;
    }


    // It is so much tedious...
    // look at the solution;
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
