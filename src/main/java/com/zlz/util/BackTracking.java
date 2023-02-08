package com.zlz.util;

import java.nio.channels.Pipe;
import java.util.*;

//
public class BackTracking {
    public static void main(String[] args) {
        System.out.println(isUgly(6));
        System.out.println(isUgly(8));
        System.out.println(isUgly(14));
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^=nums[i];
        }
        return result;

    }

    // 链表 1×2, 2×2, 3×2, 4×2, 5×2, …
    // 链表 1×3, 2×3, 3×3, 4×3, 5×3, …
    // 链表 1×5, 2×5, 3×5, 4×5, 5×5, …
    // 类似于遍历这三个链表的样式
    public int nthUglyNumber4(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;

        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }

    /**
     * dp 思想
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, …

     ** We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.**
     * this is the key point of the problem

     Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

     Every step we choose the smallest one, and move one step after,including nums with same value.


     * */

    public int nthUglyNumber2(int n) {
        int i = 0, j = 0, k = 0, p = 1;

        int[] dp = new int[n];
        dp[0] = 1;

        while (p < n) {
            dp[p] = Math.min(dp[i] * 2, Math.min(dp[j] * 3, dp[k] * 5));
            if (dp[p] == dp[i] * 2) i++;
            if (dp[p] == dp[j] * 3) j++;
            if (dp[p] == dp[k] * 5) k++;
            p++;
        }

        return dp[n - 1];
    }

    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;

        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }

    public static boolean isUgly(int n) {
        if(n == 1) return true;
        while ( n != 1 && n %2 == 0){
            n = n /2;
        }
        while ( n != 1 && n %3 == 0){
            n = n /3;
        }
        while ( n != 1 && n %5 == 0){
            n = n /5;
        }
        return n == 1;
    }

    public static  int hIndex(int[] citations) {
        Arrays.sort(citations);
        PrintUtil.p(citations);
        for(int i=0;i< citations.length;i++){
            if(citations[i] >= i) return i+1;
        }
        return 0;
    }

    /**
     * 1,2,3,4,5
     * [1,5] mid(3) = false;
     *   [4,5] mid(4) = true;
     *   [4,4] start=4,end=4
     *   return start;
     * */
    public static int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (!isBadVersion(mid)) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    private static boolean isBadVersion(int mid) {
        return true;
    }

    public static int numSquares3(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> reviewedSet = new HashSet<>();

        if(n >0) queue.offer(n);
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int val = queue.poll();
                if(!reviewedSet.add(val)) continue;

                for(int j=1; j<=Math.sqrt(val); j++){
                    if(val-(j*j) == 0) return level;
                    queue.offer(val-(j*j));
                }
            }
        }

        return level;
    }

    public static int numSquares2(int n) {

        if(n == 0) return 0;
        // 平方数直接的返回
        if(Math.sqrt(n) % 1 == 0) return 1;

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);

        int numEdges = 1;
        int upperBound = (int) Math.sqrt(n);

        while(!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int curr = q.poll();
                if(visited.contains(curr)) continue;
                visited.add(curr);

                for(int j = 1; j <= upperBound; j++) {
                    int next = curr + j * j;
                    if(next < n) q.offer(next);
                    else if(next == n) return numEdges;
                }

            }
            numEdges++;
        }
        return numEdges;
    }

    // https://www.youtube.com/watch?v=HLZLwjzIVGo&list=PLLvnKTfI-El5965mzKfymr7LmIWWqi-FY&index=2
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<= n ;i++){
            for(int s =1 ; s*s <= i; s++){
                int target = s*s;
                dp[i] = Math.min(dp[i],1+dp[i-target]);
            }
        }
        return dp[n];
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        List<String> look = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtracing(res,look, "", num, 0, target, 0, 0);
//        System.out.println(Arrays.toString(look.toArray()));
        return res;

    }

    /**
     * @param res result
     * @param tmp 临时的变量
     * @param num 输入参数
     * @param index 当前的位置
     * @param target 目标值
     * @param current 当前值
     * @param preCurrent 前一个当前值
     * */
    private static void backtracing(List<String> res,List<String> look, String tmp, String num, int index, int target, long current, long preCurrent) {
        if(index == num.length()){
            if(current == target){
                res.add(tmp);
//                look.add(tmp + " =" + current);
            }else{
//                look.add(tmp + " =" + current);
            }
            return;
        }else if(index >= num.length()){
            return;
        }

        for (int i = index; i < num.length() ; i++) {
            if(i != index && num.charAt(index) == '0') break;
            long v = Long.parseLong(num.substring(index, i + 1));
            if(index == 0){
                backtracing(res,look,String.valueOf(v),num,i+1,target,v,v);
            }else{
                backtracing(res,look,tmp+"+"+v,num,i+1,target,current+v,v);
                backtracing(res,look,tmp+"-"+v,num,i+1,target,current-v,-v);
                backtracing(res,look,tmp+"*"+v,num,i+1,target,current-preCurrent+preCurrent*v ,preCurrent*v);
            }
        }
    }

}
