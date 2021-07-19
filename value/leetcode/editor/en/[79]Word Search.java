//Given an m x n grid of characters board and a string word, return true if word
// exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cells, wher
//e adjacent cells are horizontally or vertically neighboring. The same letter cel
//l may not be used more than once. 
//
// 
// Example 1: 
//
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "ABCCED"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "SEE"
//Output: true
// 
//
// Example 3: 
//
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "ABCB"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board and word consists of only lowercase and uppercase English letters. 
// 
//
// 
// Follow up: Could you use search pruning to make your solution faster with a l
//arger board? 
// Related Topics Array Backtracking Matrix 
// 👍 6261 👎 254


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 变形的backtracking，首先画图
     * ["A","B","C","E"],
     * ["S","F","C","S"],
     * ["A","D","E","E"]
     *
     * ABCCED
     *
     * begine：开始的回溯的条件是什么？限定的剪枝条件是什么？
     *
     * */
    public static boolean exist(char[][] board, String word) {
        boolean[][] visite = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(exist(board,i,j,word,0,visite)) return true;
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, int i, int j, String word, int i1,boolean[][] visite ) {
        if(word.charAt(i1) == board[i][j]){
            if(i1 == word.length()-1){
                return true;
            }else {
                visite[i][j]=true;
                if(j<board[0].length-1 && !visite[i][j+1] && exist(board,i,j+1,word,i1+1,visite)) return true;
                if(i<board.length-1 && !visite[i+1][j]  && exist(board,i+1,j,word,i1+1,visite )) return true;
                if(j > 0 && !visite[i][j-1] && exist(board,i,j-1,word,i1+1,visite)) return true;
                if(i > 0 && !visite[i-1][j] && exist(board,i-1,j,word,i1+1,visite)) return true;
                visite[i][j]=false;
            }

        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
