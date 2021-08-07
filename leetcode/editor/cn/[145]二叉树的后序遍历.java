//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 638 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        // afterOrder(root);
        afterOrderV1(root);

        return result;
    }

    public void afterOrder(TreeNode curNode){
        if(null == curNode){
            return;
        }

        afterOrder(curNode.left);
        afterOrder(curNode.right);
        result.add(curNode.val);
    }

    public void afterOrderV1(TreeNode curNode){
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while(null != curNode || !stack.isEmpty()){
            while(null != curNode){
                stack.push(curNode);
                curNode = curNode.left;
            }

            curNode = stack.poll();
            if(null == curNode.right || prev == curNode.right){
                result.add(curNode.val);
                prev = curNode;
                curNode = null;
            } else {
                stack.push(curNode);
                curNode = curNode.right;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
