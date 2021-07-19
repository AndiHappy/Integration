//Given two strings s and t of lengths m and n respectively, return the minimum 
//window substring of s such that every character in t (including duplicates) is i
//ncluded in the window. If there is no such substring, return the empty string ""
//. 
//
// The testcases will be generated such that the answer is unique. 
//
// A substring is a contiguous sequence of characters within the string. 
//
// 
// Example 1: 
//
// 
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' fr
//om string t.
// 
//
// Example 2: 
//
// 
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.
// 
//
// Example 3: 
//
// 
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.
// 
//
// 
// Constraints: 
//
// 
// m == s.length 
// n == t.length 
// 1 <= m, n <= 105 
// s and t consist of uppercase and lowercase English letters. 
// 
//
// 
//Follow up: Could you find an algorithm that runs in O(m + n) time? Related Top
//ics Hash Table String Sliding Window 
// 👍 7211 👎 466


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty()) return "";
        Map<Character, Integer> window = new HashMap<>();
        t.chars().forEach(e->window.put((char)e, window.getOrDefault((char)e, 0) + 1));

        //i，j 为维护的窗口大小，每次的missing==0的时候，会销去可以消去的部分，保留一个最小的Window
        int i = 0, j = 0, l = 0, r = 0, missing = t.length();
        while(r < s.length()){
            char right = s.charAt(r);
            window.putIfAbsent(right, -1);
            // 这个判定的条件非常的厉害：维护窗口的滑动的条件
            if(window.get(right) > 0) {
                missing -= 1;
            }
            window.put(right, window.get(right) - 1);
            r += 1;


            while(missing == 0){
                if(j == 0 || (r - l) < (j - i)){
                    j = r;
                    i = l;
                }

                //消掉前面不合适的部分
                char left = s.charAt(l);
                window.putIfAbsent(left, -1);
                window.put(left, window.get(left) + 1);
                if(window.get(left) > 0) missing += 1;
                l += 1;
            }
        }
        return s.substring(i, j);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
