~~# 回溯法

## 背景

回溯法（backtrack）常用于遍历列表所有子集，是 DFS 深度搜索一种，一般用于全排列，穷尽所有可能，
遍历的过程实际上是一个决策树的遍历过程。时间复杂度一般 O(N!)，它不像动态规划存在重叠子问题可以优化，
回溯算法就是纯暴力穷举，复杂度一般都很高。

## 模板

```java

// 首先要确定入口函数
public  [出参]result method( [入参] input){
         result = new Object(); // 新建返回值
        // 确定回溯参数的入口，最主要的是确定回溯中用到的参数
        // 原始入参 input；结果集合 result；中间结果值：signalresult；控制参数；index 等
        backtrack(input,result,signalresult,index);
        // 返回参数
        return result;
    }

// 具体的回溯参数
private void backtrack(选择列表,路径,input,result,signalresult,index) {
if(满足条件){
     result.add(singalresult);
     return;
}

if(满足剪枝条件){
     return;//提交结束，节约资源
}  

for 选择 in 选择列表:
    做选择 // 操作signalresult
    backtrack(选择列表,路径,input,result,signalresult,index+1)
    撤销选择 // 操作signalresult,去除原来的添加
}

```

核心： 

确定向下的递归的时候的逻辑，选定递归的依据
满足的条件
不满足的条件
f(n-1) 到 f(n) 
从选择列表里做一个选择，然后一直递归往下搜索答案，如果遇到路径不通，就返回来撤销这次选择。~~
