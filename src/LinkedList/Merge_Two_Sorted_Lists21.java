package LinkedList;

public class Merge_Two_Sorted_Lists21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                dummy.next = l2;
                break;
            }
            else if (l2 == null) {
                dummy.next = l1;
                break;
            }
            else if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;

            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        return curr.next;
    }

    // think about recursion;
    // all the linkedlist related DS like LL, trie, tree, are easily use the Recursive;
    // However, This solution will result into Stack overflow error with some-thousand elements input!
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
