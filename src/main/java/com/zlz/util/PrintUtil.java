package com.zlz.util;

import java.util.*;

public class PrintUtil {

    private static final  String replace = "\\{\\}";

    private static Random random = new Random();

    public static void p(int[] o) {
        System.out.println(Arrays.toString(o));
    }


    public static void p(Object o) {
        System.out.println(o);
    }

    /**
     * [[0,0,0],[0,1,0],[0,1,0],[0,1,0],[0,0,0]] 变为int的二维数组，这样方面一些代码
     * */
    public static int[][] costructIntArray(String s) {
        String[] spit = s.split("],");
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < spit.length; i++) {
            String array = spit[i].replace("[","").replace("]","");
            String[] arrayElemet = array.split(",");
            ArrayList<Integer> arrayTmp = new ArrayList<>();
            for (String e : arrayElemet) arrayTmp.add(Integer.parseInt(e));
            result.add(arrayTmp);
        }
        int[][] a = new int[result.size()][result.get(0).size()];
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                a[i][j]=result.get(i).get(j);
            }
        }
        return a;
    }


    public static char[][] costructCharArray(String s) {
        s= s.replaceAll("\"","");
        String[] spit = s.split("],");
        List<List<Character>> result = new ArrayList<>();
        for (int i = 0; i < spit.length; i++) {
            String array = spit[i].replace("[","").replace("]","");
            String[] arrayElemet = array.split(",");
            ArrayList<Character> arrayTmp = new ArrayList<>();
            for (String e : arrayElemet) arrayTmp.add(e.charAt(0));
            result.add(arrayTmp);
        }
        char[][] a = new char[result.size()][result.get(0).size()];
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                a[i][j]=result.get(i).get(j);
            }
        }
        return a;
    }

    public static <T> void p(T[] words, int left, int right, String spit) {
        for (int i = left; i <= right ; i++) {
            String value = words[i].toString() + (spit!=null?spit:"");
            System.out.print(value );
        }
        System.out.println();
    }

    private enum Level {
        SOIUT("consoult");
        private String s;

        Level(String s) {
            this.s = s;
        }
    }

    public static void p(String format, Object... arguments) {
        info(Level.SOIUT, format, arguments);
    }

    private static void info(Level info, String format, Object[] arguments) {
        switch (info){
            case SOIUT:
                for (int i = 0; i < arguments.length; i++) {
                    format = format.replaceFirst(replace,arguments[i].toString());
                }
                System.out.println(format);
            default:
                return;
        }
    }


    public static void pIntArray(int[][] a) {
        if (a != null) {
            for (int[] element : a) {
                System.out.println(Arrays.toString(element));
            }
        }
    }

    /**
     * 构建输入的数组
     * @param max 数组最大值
     * @param leng 数组的长度 the length of array
     * @param same 是否可以包含相同的元素
     * */
    public static int[] constractArray(int max,int leng,boolean same){
        int[] result = new int[leng];
        int i = 0;
        while (i< result.length){
            int value = random.nextInt(max);
            for (int j = 0; j < i ; j++){
                if(result[j] == value) break;
            }
            result[i]=value;
            i++;
        }

        return result;
    }

    public static int[] constractArray(int max,int len){
       return constractArray(max,len,false);
    }

    public static int[] constractArray(int len){
        return constractArray(len*10,len,false);
    }

    public static int[] constractArray(int len,boolean isSame){
        return constractArray(len*10,len,isSame);
    }

    public static void pLine() {
        System.out.println("----------------------------------------------------------------------------------------");
    }


}
