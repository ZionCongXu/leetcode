//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为： 
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 104] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 
// 👍 633 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if(null == head || null == head.next){
            return;
        }

        v1(head);
    }

    public ListNode v1(ListNode head){
        Deque<ListNode> midToEndNode = splitListFromMid(head);
        ListNode curNode = head;
        while(!midToEndNode.isEmpty()){
            ListNode poll = midToEndNode.poll();
            poll.next = curNode.next;
            curNode.next = poll;
            if(null == curNode.next.next){
                curNode = curNode.next;
            } else {
                curNode = curNode.next.next;
            }
        }

        return head;
    }

    private Deque<ListNode> splitListFromMid(ListNode head){
        Deque<ListNode> stack = new LinkedList<>();
        ListNode prevSlow = head;
        ListNode slow = head;
        ListNode fast = head;
        while(null != fast && null != fast.next){
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prevSlow.next = null;
        while(null != slow){
            stack.push(slow);
            slow = slow.next;
        }

        return stack;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
