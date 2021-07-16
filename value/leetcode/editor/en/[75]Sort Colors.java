//Given an array nums with n objects colored red, white, or blue, sort them in-p
//lace so that objects of the same color are adjacent, with the colors in the orde
//r red, white, and blue. 
//
// We will use the integers 0, 1, and 2 to represent the color red, white, and b
//lue, respectively. 
//
// You must solve this problem without using the library's sort function. 
//
// 
// Example 1: 
// Input: nums = [2,0,2,1,1,0]
//Output: [0,0,1,1,2,2]
// Example 2: 
// Input: nums = [2,0,1]
//Output: [0,1,2]
// Example 3: 
// Input: nums = [0]
//Output: [0]
// Example 4: 
// Input: nums = [1]
//Output: [1]
// 
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] is 0, 1, or 2. 
// 
//
// 
// Follow up: Could you come up with a one-pass algorithm using only constant ex
//tra space? 
// Related Topics Array Two Pointers Sorting 
// 👍 6031 👎 329


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void sortColors(int[] nums) {
        int f0=0,e2 = nums.length-1;
        for (int i = 0; i < nums.length;) {
            //0,2
            if(nums[i] == 2 && i < e2){
                change(i,e2,nums);
                e2--;
            }else if (nums[i] == 0 && i > f0){
                change(i,f0,nums);
                f0++;
            }else{
                i++;
            }
        }
    }
    private static void change(int i, int e2, int[] nums) {
        int tmp = nums[i];
        nums[i]=nums[e2];
        nums[e2]=tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
