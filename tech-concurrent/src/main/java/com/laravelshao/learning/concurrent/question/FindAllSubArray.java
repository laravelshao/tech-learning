package com.laravelshao.learning.concurrent.question;

/**
 * 查找数组的所有子数组
 * <p>
 * 思路：按位与运算
 *
 * @author shaoqinghua
 * @date 2019/2/26
 * @description
 */
public class FindAllSubArray {

    public static void main(String[] args) {

        int[] originArray = {1, 2, 3, 4};
        findSubArray(originArray);
    }

    public static void findSubArray(int[] arr) {
        int length = arr.length;
        int end = 1 << length;
        for (int mark = 0; mark < end; mark++) {
            if (mark == 0) {
                System.out.print("empty array");
            } else {
                for (int i = 0; i < length; i++) {
                    if (((1 << i) & mark) != 0) {
                        if (i == length - 1) {
                            System.out.print(arr[i]);
                        } else {
                            System.out.print(arr[i] + ",");
                        }
                    }
                }
            }

            System.out.println();
        }
    }

}
