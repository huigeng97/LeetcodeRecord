package LinkedList;

import java.util.PriorityQueue;

/**
 * @author Geng Hui
 */
public class MergekSortedLists23 {

    // or Use Heap PQ to insert;

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) { return null;}
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a,b) -> (a.val - b.val));

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {pq.offer(node.next);}
        }

        return dummy.next;
    }

    // Try to use the Divide and Conquer;

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval*2) {
                lists[i] = mergeTwoList(lists[i],lists[i+interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    // merge the l2 into l1;
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
                tail = tail.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
                tail = tail.next;
            }
        }
        if(l1==null){
            tail.next=l2;
        }
        if(l2==null){
            tail.next=l1;
        }
        return dummy.next;
    }

}
