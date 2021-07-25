package A.DailyPractice;

import LinkedList.ListNode;

import java.util.List;

public class Review0722 {
    public int removeElement(int[] nums, int val) {

        int slow = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[slow++] = nums[i];
                count++;
            }
        }
        return count;
    }

    // there is another way to avoid the too much copy where the duplicate are rare;
    // by copying the last element in the current place;

    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length - 1;
        while (i <= n) {
            if (nums[i] == val) {
                nums[i] = nums[n--];
            } else {
                i++;
            }
        }
        return i;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        // this is two parts of works;
        if (lists.length == 0) {
            return null;
        }
        // divide and conquer to merge two list
        for (int interval = 1; interval < lists.length; interval = interval * 2) {
            for (int i = 0; i < lists.length; i += interval * 2) {
                if (i + interval < lists.length) {
                    lists[i] = mergeList(lists[i], lists[i + interval]);
                }

            }

        }
        return lists[0];
    }

    // merge the two list (linkedlist)
    public ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l2 != null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummy.next;
    }


    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode l1 = head;
        for (int i = 0; i < k; i++) {
            if (l1 != null) {
                l1 = l1.next;
            } else {
                return head;
            }
        }

        // first to find the next head and head; of curr;
        // l1 is the head of next one;
        ListNode tail = reverseKGroup(l1, k);

        // reverse the current k one;


        // reverse the K node;
        ListNode temp = head;
        for (int i = 0; i < k; i++) {
            head = temp;
            temp = head.next;
            head.next = tail;
            tail = head;
        }

        return head;
    }


    // Let's try KMP algorithm;



}
