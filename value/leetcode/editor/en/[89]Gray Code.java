//An n-bit gray code sequence is a sequence of 2n integers where: 
//
// 
// Every integer is in the inclusive range [0, 2n - 1], 
// The first integer is 0, 
// An integer appears no more than once in the sequence, 
// The binary representation of every pair of adjacent integers differs by exact
//ly one bit, and 
// The binary representation of the first and last integers differs by exactly o
//ne bit. 
// 
//
// Given an integer n, return any valid n-bit gray code sequence. 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: [0,1,3,2]
//Explanation:
//The binary representation of [0,1,3,2] is [00,01,11,10].
//- 00 and 01 differ by one bit
//- 01 and 11 differ by one bit
//- 11 and 10 differ by one bit
//- 10 and 00 differ by one bit
//[0,2,3,1] is also a valid gray code sequence, whose binary representation is [
//00,10,11,01].
//- 00 and 10 differ by one bit
//- 10 and 11 differ by one bit
//- 11 and 01 differ by one bit
//- 01 and 00 differ by one bit
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: [0,1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 16 
// 
// Related Topics Math Backtracking Bit Manipulation 
// 👍 1112 👎 2036


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * backtring 没有错，但是BitSet 这个数据结构，不太熟悉，这也是一个学的点
     * */
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        return helper(res, n, new BitSet());
    }

    private static List<Integer> helper(List<Integer> res, int n, BitSet chosen) {
        if (n == 0) { // all bits of chosen have been selected
            res.add(convert(chosen));
        } else {
            helper(res, n - 1, chosen);
            chosen.flip(n - 1);
            helper(res, n - 1, chosen);
        }
        return res;
    }


    public static int convert(BitSet bits) {
        int value = 0;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
