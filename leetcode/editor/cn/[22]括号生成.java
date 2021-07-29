//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1920 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<String> result = new ArrayList<>();
    private int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        match("", 0, 0);
        return result;
    }

    public void match(String cur, int left, int right){

        if(left == n && right == n){
            result.add(cur);
            return;
        }

        if(left > n || right > n || left < right){
            return;
        }

        if(left == right){
            match(cur + "(", left + 1, right);
        }

        if(left > right){
            match(cur + "(", left + 1, right);
            match(cur + ")", left, right + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
