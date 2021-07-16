//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the previou
//s row. 
// 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics Array Binary Search Matrix 
// 👍 3793 👎 209


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int f = 0,end=matrix[0].length*matrix.length-1;
        while(f <= end){
            int mid = f+(end-f)/2;
            if(matrix[mid/matrix[0].length][mid%matrix[0].length]== target) {
                return true;
            }else if(matrix[mid/matrix[0].length][mid%matrix[0].length] > target){
                end=mid-1;
            }else {
                f=mid+1;
            }
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
