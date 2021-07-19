//Given an integer array nums of unique elements, return all possible subsets (t
//he power set). 
//
// The solution set must not contain duplicate subsets. Return the solution in a
//ny order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// Example 2: 
//
// 
//Input: nums = [0]
//Output: [[],[0]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// All the numbers of nums are unique. 
// 
// Related Topics Array Backtracking Bit Manipulation 
// 👍 6418 👎 118


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 全部的子集
     * */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length ; i++) {
            subsets(nums,0,i,result,new HashSet<Integer>());
        }
        return result;
    }

    private static void subsets(int[] nums, int i, int k, List<List<Integer>> result, HashSet<Integer> es) {
        if(es.size() == k){
            result.add(new ArrayList<>(es));
            return;
        }else{
            for (int j = i; j <nums.length ; j++) {
                es.add(nums[j]);
                subsets(nums,j+1,k,result,es);
                es.remove(nums[j]);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
