//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1138 👎 0


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
    Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        indexMap = new HashMap<>(len - 1);
        for(int i = 0; i < len; i++){
            indexMap.put(inorder[i], i);
        }

        return myBuildTreeV1(preorder, inorder, 0, len - 1, 0, len - 1);
    }

    public TreeNode myBuildTreeV1(int[] preOrder, int[]inOrder,
        int preOderLeftIndex, int preOrderRightIndex, int inOrderLeftIndex,
        int inOrderRightIndex){

        if(preOderLeftIndex > preOrderRightIndex){
            return null;
        }

        // 前序遍历的第一个节点就是根节点
        int rootIndexWithPreOrder = preOderLeftIndex;

        // 得到根节点在中序遍历中的位置
        int rootIndexWithInOrder =
            indexMap.get(preOrder[rootIndexWithPreOrder]);

        // 构建根节点
        TreeNode root = new TreeNode(preOrder[rootIndexWithPreOrder]);

        // 得到左子树的节点数目
        int leftSubTreeSize = rootIndexWithInOrder - inOrderLeftIndex;

        // 递归构造左子树并连接到根节点
        // 前序遍历中[preOderLeftIndex + 1, leftSubTreeSize + preOderLeftIndex ]
        // 个元素对应了中序遍历中[inOrderLeftIndex, rootIndexWithInOrder - 1]的元素
        root.left = myBuildTreeV1(preOrder, inOrder, preOderLeftIndex + 1,
            leftSubTreeSize + preOderLeftIndex, inOrderLeftIndex,
            rootIndexWithInOrder - 1);

        // 递归构建右子树并连接到根节点
        // 前序遍历中[preOderLeftIndex + leftSubTreeSize + 1, preOrderRightIndex]
        // 的元素就对应了中序遍历中[rootIndexWithInOrder + 1, inOrderRightIndex]的元素
        root.right = myBuildTreeV1(preOrder, inOrder,
            preOderLeftIndex + leftSubTreeSize + 1, preOrderRightIndex,
            rootIndexWithInOrder + 1, inOrderRightIndex);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
