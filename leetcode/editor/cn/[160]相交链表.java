//给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。 
//
// 图示两个链表在节点 c1 开始相交： 
//
// 
//
// 题目数据 保证 整个链式结构中不存在环。 
//
// 注意，函数返回结果后，链表必须 保持其原始结构 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, sk
//ipB = 3
//输出：Intersected at '8'
//解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
//从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
//在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 
//1
//输出：Intersected at '2'
//解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
//从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
//在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
//由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
//这两个链表不相交，因此返回 null 。
// 
//
// 
//
// 提示： 
//
// 
// listA 中节点数目为 m 
// listB 中节点数目为 n 
// 0 <= m, n <= 3 * 104 
// 1 <= Node.val <= 105 
// 0 <= skipA <= m 
// 0 <= skipB <= n 
// 如果 listA 和 listB 没有交点，intersectVal 为 0 
// 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1] 
// 
//
// 
//
// 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？ 
// Related Topics 哈希表 链表 双指针 
// 👍 1288 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(null == headA || null == headB){
            return null;
        }

        //return v1(headA, headB);
        // return v2(headA, headB);
        return v3(headA, headB);
    }

    /**
     * 利用 set
     * @param headA
     * @param headB
     * @return
     */
    public ListNode v1(ListNode headA, ListNode headB){
        Set<ListNode> existedNodes = new HashSet<>();
        ListNode curA = headA;
        ListNode curB = headB;

        while(null != curA){
            existedNodes.add(curA);
            curA = curA.next;
        }

        while(null != curB){
            if(existedNodes.contains(curB)){
                return curB;
            }

            curB = curB.next;
        }

        return null;
    }

    /**
     * 利用总长度的差值
     * 假设 ListA 长度是 LA， ListB 长度是 LB，相交的长度是 C
     * 那么如果 LA 大于 LB，那么想让 ListA 遍历到 LA - LB 地方，然后再同时遍历 ListA 和  ListB，就能得到相交点
     * 同理 LB 大于 LA 时一样处理
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode v2(ListNode headA, ListNode headB){
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = getLen(curA);
        int lenB = getLen(curB);

        curA = headA;
        curB = headB;

        if(lenA > lenB){
            int diff = lenA - lenB;
            while(diff > 0){
                curA = curA.next;
                diff--;
            }
        } else {
            int diff = lenB - lenA;
            while(diff > 0){
                curB = curB.next;
                diff--;
            }
        }

        // 如果有相交的节点，那么遍历完成时必有相等的节点
        while(null != curA || null != curB){
            if(curA == curB){
                return curA;
            }

            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }

    private int getLen(ListNode head){
        int i = 0;
        while(null != head){
            i++;
            head = head.next;
        }

        return i;
    }

    /**
     * 也是利用差值进行遍历
     * 和上面不同的是，这里不记录差值，而且利用差值进行遍历节点的重置。具体过程如下：
     * 假设 A 和 B 有相交的节点，那么同时遍历 A 和 B ，当 cruA 遍历到末尾节点时，那么
     * 把 curA 节点指向 headB节点，当 curB 遍历到末尾节点是，把 curB 指向 headA 节点。
     * 这样就可以保证 curA 和  cruB 走过的长度是一致的。所以当 curA 和 curB 相等时，就是相交节点
     *
     * 如果没有相交节点，那么都会同时遍历到尾节点，直接返回
     * @param headA
     * @param headB
     * @return
     */
    public ListNode v3(ListNode headA, ListNode  headB){
        ListNode curA = headA;ls
        ListNode curB = headB;

        while(curA != curB){
            curA = null == curA ? headB : curA.next;
            curB = null == curB ? headA : curB.next;
        }

        return curA;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
