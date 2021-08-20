package com.zlz.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
    public static void main(String[] args) {
        System.out.println("keep Happy boy");

        PrintUtil.p(addOperators("1234",10));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        List<String> look = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtracing(res,look, sb, num, 0, target, 0, 0);
        System.out.println(Arrays.toString(look.toArray()));
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
    private static void backtracing(List<String> res,List<String> look, StringBuilder tmp, String num, int index, int target, int current, int preCurrent) {
        if(index == num.length()){
            if(current == target){
                res.add(tmp.toString());
                look.add(tmp.toString() + " = " + current);
            }else{
                look.add(tmp.toString() + " = " + current);
            }
            return;
        }else if(index >= num.length()){
            return;
        }


        int v = num.charAt(index)-'0';
        if(index == 0){
            tmp.append(v);
            backtracing(res,look,tmp,num,index+1,target,v,preCurrent);
        }else{
            backtracing(res,look,tmp.append("+").append(v),num,index+1,target,current+v,v);
            tmp.delete(tmp.length()-2,tmp.length());
            backtracing(res,look,tmp.append("-").append(v),num,index+1,target,current-v,-v);
            tmp.delete(tmp.length()-2,tmp.length());
            backtracing(res,look,tmp.append("*").append(v),num,index+1,target,current-preCurrent+preCurrent*v ,preCurrent*v);
            tmp.delete(tmp.length()-2,tmp.length());
        }




    }

}
