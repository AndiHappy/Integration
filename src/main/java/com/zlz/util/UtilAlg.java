package com.zlz.util;

import java.util.*;

public class UtilAlg {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(subsetsWithDup(new int[]{1,2,3}));
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }

    /**
     * 还是需要遵守我们规划出来的步骤
     * 1. 确认输入，输出：
     * 【1，2，2】
     * [[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 2. 规律化
     * 1，2，2
     * []
     * 1|2
     * 1,2|2,2
     * 1,2,2
     * */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length ; i++) {
            subsetsWithDup(nums,0,i,result,new ArrayList<Integer>());
        }
        return result;
    }

    private static void subsetsWithDup(int[] nums, int i, int k, List<List<Integer>> result, ArrayList<Integer> es) {
        if(es.size() == k){
            result.add(new ArrayList<>(es));
            return;
        }else{
            for (int j = i; j <nums.length ; j++) {
                if(j >i && nums[j-1] == nums[j]) continue;
                es.add(nums[j]);
                subsetsWithDup(nums,j+1,k,result,es);
                es.remove(es.size()-1);
            }
        }
    }




    public static void test_grayCode(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(grayCode(3));
    }


    /**
     * backtring 没有错，但是BitSet 这个数据结构，不太熟悉，这也是一个学的点
     * */
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        return helper(res, n, new BitSet());
    }

    private static List<Integer> helper(List<Integer> res, int n, BitSet chosen) {
        if (n == 0) { // all bits of chosen have been selected
            Integer va = convert(chosen);
            res.add(va);
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


    public static void test_exist(String[] args) {
        System.out.println("keep Happy boy");
        char[][] v = PrintUtil.costructCharArray("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]");
        System.out.println(exist(v,"ABCCEDA"));
        System.out.println(exist(v,"ABCCED"));

        v = PrintUtil.costructCharArray("[[\"a\",\"b\"],[\"c\",\"d\"]]");
        System.out.println(exist(v,"acdb"));

        v = PrintUtil.costructCharArray("[[\"C\",\"A\",\"A\"],[\"A\",\"A\",\"A\"],[\"B\",\"C\",\"D\"]]");
        System.out.println(exist(v,"AAB"));
    }


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
     * [a,b]
     * [c,d]
     *
     * acdb
     *
     * [["C","A","A"],
     * ["A","A","A"],
     * ["B","C","D"]
     *
     * AAB
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

    public static void test_subsets(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(subsets(new int[]{1}));
    }


    /**
     * nums = [1,2,3]
     * k=0, []
     * k=1, 1,2,3
     * k=2, 1,2|1,3|2,3
     * k=3, 1,2,3
     *
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


    public static void test_combine(String[] args) {
        System.out.println("keep Happy boy");
        List<List<Integer>> resul = combine(4,2);
        PrintUtil.p(resul);
    }

    /**
     * 最标准的一个递归迭代
     * 1，2 | 1，3| 1，4
     * 2，3，| 2，3
     * 3，4
     *
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


    public static void test_minWindow(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(minWindow("ADOBCAODEBANC","ABC"));
        System.out.println(minWindow("ADOBECODEBANC","ABC"));

    }

    /**
     * 我们迫切的需要考虑的是，窗口的维护
     * 如果我们，碰到第一个匹配的时候，我们直接把我们设置的Map清空以后，再次的开始
     * 这样的话，我们就放弃了，前面匹配的内容，就不能够平滑的滑动，只是在跳跃的匹配，这样不合题目中的要求
     *
     * 现在我们面临的问题是，如果确定窗口的滑动过程中的更新，我们可以列出来，我们已经知晓的内容：
     *
     *
     * */
    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty()) return "";
        Map<Character, Integer> need = new HashMap<>();
        t.chars().forEach(e->need.put((char)e, need.getOrDefault((char)e, 0) + 1));
        int i = 0, j = 0, l = 0, r = 0, missing = t.length();
        while(r < s.length()){
            char right = s.charAt(r);
            need.putIfAbsent(right, -1);
            // 这个判定的条件非常的厉害：维护窗口的滑动的条件
            if(need.get(right) > 0) {
                missing -= 1;
            }
            need.put(right, need.get(right) - 1);
            r += 1;
            while(missing == 0){//窗口的维护，尽量的找到
                if(j == 0 || (r - l) < (j - i)){
                    j = r;
                    i = l;
                }
                char left = s.charAt(l);
                need.putIfAbsent(left, -1);
                need.put(left, need.get(left) + 1);
                if(need.get(left) > 0) missing += 1;
                l += 1;
            }
        }
        return s.substring(i, j);
    }

    public static void test_sortColors(String[] args) {
        System.out.println("keep Happy boy");
        int[] a = PrintUtil.constractArray(3,10,false);
        PrintUtil.p(Arrays.toString(a));
        sortColors(a);
        PrintUtil.p(Arrays.toString(a));
        a=new int[]{0};
        sortColors(a);
        PrintUtil.p(Arrays.toString(a));

        a=new int[]{1};
        sortColors(a);
        PrintUtil.p(Arrays.toString(a));

        a=new int[]{1,0};
        sortColors(a);
        PrintUtil.p(Arrays.toString(a));

    }


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


    public static void test_searchMatrix(String[] args) {
        System.out.println("keep Happy boy");
        int[] a = PrintUtil.constractArray(10);
        PrintUtil.p(Arrays.toString(a));
        Arrays.sort(a);
        PrintUtil.p(Arrays.toString(a));
        System.out.println(findByDivedMethod(a,45));

        int[][] tes = PrintUtil.costructIntArray("[[1,1]]");
        System.out.println(searchMatrix(tes,2));
    }

    public static  int findByDivedMethod(int[] a,int target){
        int f = 0,end=a.length;
        while(f <= end){
            int mid = f+(end-f)/2;
            if(a[mid]== target) {
                return mid;
            }else if(a[mid] > target){
                end=mid-1;
            }else {
                f=mid+1;
            }
        }
        return -1;
    }

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
