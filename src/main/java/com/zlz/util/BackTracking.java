package com.zlz.util;

import java.util.*;

//
public class BackTracking {
    public static void main(String[] args) {
        System.out.println("keep Happy boy");

        PrintUtil.p(numSquares(12));
        PrintUtil.p(numSquares(13));
        PrintUtil.p(numSquares2(13));
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
