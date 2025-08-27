package leetcode;

import java.util.*;

/**
 * LeetCode 2807. Insert Greatest Common Divisors in Linked List
 *
 * You are given the head of a linked list of positive integers.
 * For every pair of adjacent nodes, insert a new node with value equal to the greatest common divisor (GCD)
 * of the values of the two nodes. Return the head of the modified linked list.
 *
 * Example 1:
 *   Input: head = [18,6,10,3]
 *   Output: [18,6,6,2,10,1,3]
 *
 * Example 2:
 *   Input: head = [7]
 *   Output: [7]
 *
 * Constraints:
 *   - The number of nodes in the list is in the range [1, 5000].
 *   - 1 <= Node.val <= 1000
 */
public class LeetCode2807_InsertGreatestCommonDivisorsInLinkedList {

    public static void main(String[] args) {
        // Example cases
        runTest(new int[]{18,6,10,3}, new int[]{18,6,6,2,10,1,3});
        runTest(new int[]{7},          new int[]{7});

        // Additional cases
        runTest(new int[]{1,1},        new int[]{1,1,1});
        runTest(new int[]{2,4,8},      new int[]{2,2,4,4,8});
        runTest(new int[]{5,10,15},    new int[]{5,5,10,5,15});
        runTest(new int[]{9,28},       new int[]{9,1,28});
        runTest(new int[]{3,5,7},      new int[]{3,1,5,1,7});
    }

    private static void runTest(int[] input, int[] expected) {
        ListNode head = ListNode.fromArray(input);
        ListNode out  = new Solution2807().insertGreatestCommonDivisors(head);
        int[] actual  = ListNode.toArray(out);

        System.out.println("input    : " + Arrays.toString(input));
        System.out.println("expected : " + Arrays.toString(expected));
        System.out.println("actual   : " + Arrays.toString(actual));
        System.out.println(Arrays.equals(expected, actual) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

// Minimal ListNode for local testing
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    static ListNode fromArray(int[] arr) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : arr) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }
    static int[] toArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) list.add(p.val);
        return list.stream().mapToInt(i->i).toArray();
    }
}

class Solution2807 {
    /**
     * Insert a GCD node between every pair of adjacent nodes and return the head.
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = new ListNode(gcd(head.val, head.next.val), head.next);
        insertGreatestCommonDivisors(head.next.next);
        return head;
    }
    int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}