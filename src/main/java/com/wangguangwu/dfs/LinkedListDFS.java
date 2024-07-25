package com.wangguangwu.dfs;

/**
 * Linked List DFS implementation.
 *
 * @author wangguangwu
 */
public class LinkedListDFS {

    public void dfs(ListNode head) {
        if (head == null) {
            return;
        }

        // Process the current node
        System.out.println(head.val);

        // Recursively visit all children
        for (ListNode child : head.children) {
            dfs(child);
        }

        // Move to the next node at the same level
        dfs(head.next);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode child1 = new ListNode(6);
        ListNode child2 = new ListNode(7);

        node2.children.add(child1);
        node2.children.add(child2);

        LinkedListDFS listDfs = new LinkedListDFS();
        System.out.println("Linked list DFS traversal:");
        listDfs.dfs(head);
    }
}
