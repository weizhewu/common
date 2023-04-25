package com.waltz.utils.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author MrWei
 * @version 1.0.0
 * @date 2023/4/25 23:06
 * @description 选择排序
 */
public class SelectSort {
    public static List<Integer> sortAsc(List<Integer> numList){
        if (Objects.isNull(numList) || numList.size() <= 1){
            return numList;
        }
        int listSize = numList.size();
        int index;
        for (int i = 1;i < listSize;i++){
            index = i-1;
            for (int j = i;j < listSize;j++){
                if (numList.get(j) < numList.get(index)){
                    index = j;
                }
            }
            if (index != (i-1)){
                int tem = numList.get(i-1);
                numList.set(i-1,numList.get(index));
                numList.set(index,tem);
            }

        }
        return numList;
    }

    public static List<Integer> sortDesc(List<Integer> numList) {
        if (Objects.isNull(numList) || numList.size() <= 1) {
            return numList;
        }
        int listSize = numList.size();
        int index;
        for (int i = 1; i < listSize; i++) {
            index = i - 1;
            for (int j = i; j < listSize; j++) {
                if (numList.get(j) > numList.get(index)) {
                    index = j;
                }
            }
            if (index != (i - 1)) {
                int tem = numList.get(i - 1);
                numList.set(i - 1, numList.get(index));
                numList.set(index, tem);
            }

        }
        return numList;
    }

    public static int[] sortAsc(int[] numArr){
        if (Objects.isNull(numArr) || numArr.length <= 1){
            return numArr;
        }
        int length = numArr.length;
        int index;
        for (int i = 1;i < length;i++){
            index = i-1;
            for (int j = i;j < length;j++){
                if (numArr[j] < numArr[index]){
                    index = j;
                }
            }
            if (index!=(i-1)){
                int tem = numArr[i-1];
                numArr[i-1] = numArr[index];
                numArr[index] = tem;
            }

        }
        return numArr;
    }

    public static int[] sortDesc(int[] numArr){
        if (Objects.isNull(numArr) || numArr.length <= 1){
            return numArr;
        }
        int length = numArr.length;
        int index;
        for (int i = 1;i < length;i++){
            index = i-1;
            for (int j = i;j < length;j++){
                if (numArr[j] > numArr[index]){
                    index = j;
                }
            }
            if (index != (i-1)){
                int tem = numArr[i-1];
                numArr[i-1] = numArr[index];
                numArr[index] = tem;
            }

        }
        return numArr;
    }
}
