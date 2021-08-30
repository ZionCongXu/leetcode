//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1525 👎 0


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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // return v1(head, n);
        return v2(head, n);
        // return v3(head, n);
    }

    /**
     * 求长度
     * @param head
     * @param n
     * @return
     */
    public ListNode v1(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        int size = 0;
        ListNode cur = head;
        while(null != cur){
            ++size;
            cur = cur.next;
        }

        cur = dummy;
        for(int i = 0; i < size - n; i++){
            cur = cur.next;
        }

        ListNode next = cur.next;
        cur.next = next.next;
        next = null;

        return dummy.next;
    }

    /**
     * 栈
     * @param head
     * @param n
     * @return
     */
    public ListNode v2(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while(null != cur){
            stack.push(cur);
            cur = cur.next;
        }

        for(int i = 0; i < n; i++){
            stack.pop();
        }

        ListNode prev = stack.peek();
        ListNode next = prev.next;
        prev.next = next.next;
        next = null;

        return dummy.next;
    }

    /**
     * 双指针
     */
    public ListNode v3(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = dummy;

        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        while(null != fast){
            fast = fast.next;
            slow = slow.next;
        }

        ListNode next = slow.next;
        slow.next = next.next;
        next = null;

        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
