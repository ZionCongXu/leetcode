//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 480 👎 0


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
    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        bfs(root, 0);

        return results;
    }

    public void bfs(TreeNode root, int depeth){
        if(null == root){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isOrderLeft = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            Deque<Integer> result = new LinkedList<>();

            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.poll();
                if(isOrderLeft){
                    result.offerLast(curNode.val);
                } else {
                    result.offerFirst(curNode.val);
                }

                if(null != curNode.left){
                    queue.offer(curNode.left);
                }

                if(null != curNode.right){
                    queue.offer(curNode.right);
                }
            }

            results.add(new LinkedList<Integer>(result));
            isOrderLeft = !isOrderLeft;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
