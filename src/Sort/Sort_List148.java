package Sort;


import LinkedList.ListNode;

import java.util.LinkedList;
import java.util.List;

//Merge Sort (divide and conquer)
// recursively split the original

//Space Complexity: O(logn) , where nn is the number of nodes in linked list.
// Since the problem is recursive, we need additional space to store the recursive call stack.
// The maximum depth of the recursion tree is log(n) .
public class Sort_List148 {

    // First ito get the mid of the list;
    // sort the left part;
    // sort the right part;
    // merge them together
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    // get the middle of the list head;
    // don't forget to split the mid and the previous one;
    public ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        ListNode fast = head;
        while (fast != null || fast.next != null) {
            midPrev =  (midPrev == null) ? head : midPrev.next;
            fast = fast.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;

        }
        tail.next = (left == null) ? right : left;
        return  dummyHead.next;
    }


    //This is a bottom-up method;
    // The top down method required the extro place for the recursive stake, however the top down only takes the const
    // extra space.
    // so what is the bottom up method;
    // One way I could come up with is to first sort every two list, and then every four list, until the final list;
    ListNode tail = new ListNode();
    ListNode nextSubList = new ListNode();

    public ListNode  sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // iterative from size = 1 to the size  < n;
        int n = getCount(head);
        ListNode start = head;
        ListNode dummyHead = new ListNode();
        for (int size = 1; size < n; size *= 2) {
            // fist to sort by interval of size and merge
            tail = dummyHead;
            while (start != null) {
                // 不是很懂这个地方，为什么要tail.next = start 之后后面还有 start = dummy.mext;
                if (start.next == null) {
                    tail.next = start;
                    break;
                }
                // find the next mid;
                ListNode mid = split(start, size);
                merge2(start, mid);
                // come to next section;
                start = nextSubList;
            }
            start = dummyHead.next;
        }
        return dummyHead.next;
    }

    public ListNode split(ListNode start, int size) {
        ListNode midPrev = start;
        ListNode end = start.next;
        // find the midPrev and the end of the second list;
        //  TODO : This part is not finished


        // reconstruct the connection based on the diagram
        ListNode mid = midPrev.next;
        midPrev.next = null;
        nextSubList = end.next;
        end.next = null;
        return mid;
    }

    public int getCount(ListNode head) {
        int count = 0;
        while (head!=null) {
            head = head.next;
            count++;
        }
        return  count;
    }

    public void merge2(ListNode list1, ListNode list2) {

    }

}
