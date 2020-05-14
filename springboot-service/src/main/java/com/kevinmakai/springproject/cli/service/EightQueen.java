package com.kevinmakai.springproject.cli.service;

/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-05-09 12:55
 */
public class EightQueen {
    int max = 8 ;
    int[] array = new int[max];
    int count = 0;

    public boolean judge(int n){
        for(int i =0;i<n;i++){
            /**
             * if语句用于判断皇后的位置是否满足条件
             * 1.横着不能位于同一行；因为皇后按行放，所以一行只可能有一个皇后，这条一定成立
             * 2.竖着不能位于同一列，数组的值value就代表皇后位于的列数，只要皇后n+1与之前的n个皇后的value
             * 值都不同，就说明成立
             * 3.不能位于同一斜线，仔细观察棋盘，同一斜线即斜率k=45度。
             * 当横坐标之差 = 纵坐标之差就说明这两个相比较的皇后不位于同一斜线上。
             */
            if(array[i] == array[n] || Math.abs(array[i]-array[n]) == Math.abs(i-n)){
                return false;
            }
        }
        return true;
    }

    public void check(int n){
        if(n==max){
            for(int s:array){
                System.out.print(s);
            }
            System.out.println();
            return;
        }
        for(int i =0;i<max;i++){
            array[n] = i;
            if(judge(n)){
                check(n+1);
            }
        }
    }

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
    }
}
