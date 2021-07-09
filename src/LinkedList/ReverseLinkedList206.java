package LinkedList;

public class ReverseLinkedList206 {

        // there are two ways to solve this problem;
    // recursive and iterative;

    // recursive
    public ListNode reverseList(ListNode head) {
        if (head == null) {return null;}
        ListNode[] nodes = reverseHelper(head);
        return nodes[0];
    }

    private ListNode[] reverseHelper(ListNode head) {
        if (head.next == null) {return new ListNode[]{head,head};}
        // we need to return two item,
        // nodes[0] is the new head,
        // nodes[1] is the new end;
        ListNode end = head;
        ListNode[] nodes = reverseHelper(head.next);
        nodes[1].next = end;
        end.next = null;
        return new ListNode[]{nodes[0],end};
    }

    public ListNode reverseList2(ListNode head, int[] a) {
        a[0] = 1;
        if (head == null) {return null;}
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public void test() {
        ListNode head = new ListNode(3,new ListNode(2));
        head.next.next = new ListNode(1,new ListNode(0));
        int[] a = new int[]{2,3};
        System.out.println(head);
        reverseList2(head,a);
        System.out.println(head.val);
        System.out.println(head.next);
    }

  public static void main(String[] args) {
        //
//      ListNode head = new ListNode(3,new ListNode(2));
//      head.next.next = new ListNode(1,new ListNode(0));
      ReverseLinkedList206 test = new ReverseLinkedList206();
      test.test();
  }
}
