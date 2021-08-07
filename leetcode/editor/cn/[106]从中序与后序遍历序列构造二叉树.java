//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 542 👎 0


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
    Map<Integer, Integer> inOrderNodeIndexMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = inorder.length;
        inOrderNodeIndexMap = new HashMap<>(length);
        for(int i = 0; i < length; i++){
            inOrderNodeIndexMap.put(inorder[i], i);
        }

        return myBuildTree(inorder, postorder, 0, length -1, 0, length -1);
    }

    public TreeNode myBuildTree(int[] inOrder, int[] postOrder,
        int inOrderLeftIndex, int inOrderRightIndex, int postOrderLeftIndex,
        int postOrderRightIndex){
        if(inOrderLeftIndex > inOrderRightIndex){
            return null;
        }

        // 后序遍历的最后一个节点就是root 节点
        int rootIndexWithPostOrder = postOrderRightIndex;

        // 得到 root 节点在中序遍历中的位置
        int rootIndexWithInOrder =
            inOrderNodeIndexMap.get(postOrder[rootIndexWithPostOrder]);

        // 构建 root 节点
        TreeNode root = new TreeNode(postOrder[rootIndexWithPostOrder]);

        // 构建左孩子
        // 中序遍历中 [inOrderLeftIndex, rootIndexWithInOrder - 1] 和 后序遍历中
        // [postOrderLeftIndex, rootIndexWithInOrder - 1]
        root.left = myBuildTree(inOrder, postOrder, inOrderLeftIndex,
            rootIndexWithInOrder - 1, postOrderLeftIndex,
            rootIndexWithInOrder - 1);

        // 构建右孩子
        // 中序遍历中 [rootIndexWithInOrder + 1, inOrderRightIndex] 和 后续遍历中
        // [postOrderLeftIndex + inOrderRightIndex, postOrderRightIndex]
        root.right = myBuildTree(inOrder, postOrder, rootIndexWithInOrder + 1,
            inOrderRightIndex, rootIndexWithInOrder + 1,
            postOrderRightIndex - 1);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
