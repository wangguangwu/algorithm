package com.wangguangwu.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangguangwu
 */
public class ListNode {

    int val;
    List<ListNode> children;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}
