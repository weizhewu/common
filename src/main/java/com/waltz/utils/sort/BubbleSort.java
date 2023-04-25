package com.waltz.utils.sort;

import java.util.List;
import java.util.Objects;

/**
 * @author MrWei
 * @version 1.0.0
 * @date 2023/4/25 22:41
 * @description 冒泡排序
 */
public class BubbleSort {
    public static List < Integer > sortAsc(List < Integer > numList){
        if (Objects.isNull(numList) || numList.size() <= 1){
            return numList;
        }
        int listSize = numList.size();
        for (int i = 1;i < listSize;i++){
            boolean confirm = true;
            for (int j = 0;j < listSize-i;j++){
                int front = numList.get(j);
                int behind = numList.get(j+1);
                if (front > behind){
                    numList.set(j,behind);
                    numList.set(j+1,front);
                    confirm = false;
                }
            }
            if (confirm){
                break;
            }
        }
        return numList;
    }

    public static List < Integer > sortDesc(List < Integer > numList){
        if (Objects.isNull(numList) || numList.size() <= 1){
            return numList;
        }
        int listSize = numList.size();
        for (int i = 1;i < listSize;i++){
            boolean confirm = true;
            for (int j = 0;j < listSize-i;j++){
                int front = numList.get(j);
                int behind = numList.get(j+1);
                if (front < behind){
                    numList.set(j,behind);
                    numList.set(j+1,front);
                    confirm = false;
                }
            }
            if (confirm){
                break;
            }
        }
        return numList;
    }

    public static int[] sortAsc(int[] numArr){
        if (Objects.isNull(numArr) || numArr.length <= 1){
            return numArr;
        }
        int length = numArr.length;
        for (int i = 1;i < length;i++){
            boolean confirm = true;
            for (int j = 0;j < length-i;j++){
                int front = numArr[j];
                int behind = numArr[j+1];
                if (front > behind){
                    numArr[j+1] = front;
                    numArr[j] = behind;
                    confirm = false;
                }
            }
            if (confirm){
                break;
            }
        }
        return numArr;
    }

    public static int[] sortDesc(int[] numArr){
        if (Objects.isNull(numArr) || numArr.length <=  1){
            return numArr;
        }
        int length = numArr.length;
        for (int i = 1;i < length;i++){
            boolean confirm = true;
            for (int j = 0;j < length-i;j++){
                int front = numArr[j];
                int behind = numArr[j+1];
                if (front < behind){
                    numArr[j+1] = front;
                    numArr[j] = behind;
                    confirm = false;
                }
            }
            if (confirm){
                break;
            }
        }
        return numArr;
    }
}
