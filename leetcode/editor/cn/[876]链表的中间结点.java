//给定一个头结点为 head 的非空单链表，返回链表的中间结点。 
//
// 如果有两个中间结点，则返回第二个中间结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = 
//NULL.
// 
//
// 示例 2： 
//
// 
//输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
// 
//
// 
//
// 提示： 
//
// 
// 给定链表的结点数介于 1 和 100 之间。 
// 
// Related Topics 链表 双指针 
// 👍 374 👎 0


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
    public ListNode middleNode(ListNode head) {
        // return v1(head);
        // return v2(head);
        return v3(head);
    }

    /**
     * 借助数组，返回数组的中间节点即可
     * @param head
     * @return
     */
    public ListNode v1(ListNode head){
        ListNode[] tmp = new ListNode[100];
        ListNode headTmp = head;
        int i = 0;
        while(null != headTmp){
            tmp[i++] = headTmp;
            headTmp = headTmp.next;
        }

        System.out.println(i);

        return tmp[i / 2];
    }

    /**
     * 两次遍历
     * @param head
     * @return
     */
    public ListNode v2(ListNode head){
        int length = 0;
        ListNode headTmp = head;
        while(null != headTmp){
            length++;
            headTmp = headTmp.next;
        }

        headTmp = head;
        for(int i = 0; i < length / 2; i++){
            headTmp = headTmp.next;
        }

        return headTmp;
    }

    /**
     * 快慢指针法
     * @param head
     * @return
     */
    public ListNode v3(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
