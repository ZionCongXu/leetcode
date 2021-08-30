//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 1005 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int column = board.length;
        int row = board[0].length;
        int wordLength = word.length();
        boolean[][] visited = new boolean[column][row];
        for(int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                boolean result = dfs(board, visited, word, wordLength,0
                    , column, row, i, j);
                if(result){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, boolean[][] visited, String word,
        int wordLength, int cur, int column, int row, int curCol, int curRow
        ){
        if(board[curCol][curRow] != word.charAt(cur)){
            return false;
        } else if(cur == wordLength - 1){
            return true;
        }

        visited[curCol][curRow] = true;
        boolean result = false;
        for(int[] dir : directions){
            int colTmp = curCol + dir[0];
            int rowTmp = curRow + dir[1];

            if(colTmp >= 0 && colTmp < column && rowTmp >= 0 && rowTmp < row){
                if(!visited[colTmp][rowTmp]){
                    boolean flag = dfs(board, visited, word, wordLength,
                        cur + 1, column, row, colTmp, rowTmp);
                    if(flag){
                        result = true;
                        break;
                    }
                }
            }
        }

        visited[curCol][curRow] = false;
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
