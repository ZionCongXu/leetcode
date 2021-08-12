//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 1249 👎 0


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
    public ListNode sortList(ListNode head) {
        return mergeSort(head, null);
    }

    public ListNode mergeSort(ListNode head, ListNode tail){
        if(null == head){
            return head;
        }

        if(head.next == tail){
            head.next = null;
            return head;
        }

        ListNode midNode = midNode(head, tail);
        ListNode leftList = mergeSort(head, midNode);
        ListNode rightList = mergeSort(midNode, tail);
        ListNode sortedNode = merge(leftList, rightList);

        return sortedNode;
    }

    /**
     * 利用快慢指针找到中点
     * @return
     */
    public ListNode midNode(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        while(null != fast && null != fast.next && tail != fast && tail != fast.next){
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(slow.val);
        return slow;
    }

    public ListNode merge(ListNode left, ListNode right){
        ListNode dummyHead = new ListNode(0);
        ListNode tmp = dummyHead, tmp1 = left, tmp2 = right;
        while (null != tmp1 && null != tmp2){
            if(tmp1.val <= tmp2.val){
                tmp.next = tmp1;
                tmp1 = tmp1.next;
            } else {
                tmp.next = tmp2;
                tmp2 = tmp2.next;
            }

            tmp = tmp.next;
        }

        if(null != tmp1){
            tmp.next = tmp1;
        } else if(null != tmp2){
            tmp.next = tmp2;
        }

        return dummyHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
