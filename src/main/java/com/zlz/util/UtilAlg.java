package com.zlz.util;

import java.util.Arrays;

public class UtilAlg {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");

    }

    public String minWindow(String s, String t) {
        return "";
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
