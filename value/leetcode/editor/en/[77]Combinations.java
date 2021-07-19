//Given two integers n and k, return all possible combinations of k numbers out 
//of the range [1, n]. 
//
// You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
//
// Example 2: 
//
// 
//Input: n = 1, k = 1
//Output: [[1]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics Array Backtracking 
// 👍 2531 👎 89


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 从n个元素中，抽出来k个元素，组成一个组合
     * */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(n,1,k,result,new HashSet<Integer>());
        return result;
    }

    private static void combine(int n, int i, int k, List<List<Integer>> result, HashSet<Integer> integers) {
        if(integers.size() == k){
            result.add(new ArrayList<>(integers));
            return;
        }else {
            for (int j = i; j <= n; j++) {
                integers.add(j);
                combine(n,j+1,k,result,integers);
                integers.remove(j);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
