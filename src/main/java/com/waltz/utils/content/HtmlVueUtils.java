package com.waltz.utils.content;

import java.util.*;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: vue、html内容获取工具
 * @createDate: 2023/4/26 11:01
 **/
public class HtmlVueUtils {
    /**
     * 获取content中，指定标签的开始位置与结束位置
     * @param content vue、html的内容
     * @param tag 标签 如div、html这种
     * @param keyWords tag的限定词，如class=""，这个限定词是紧跟tag后面的内容，如<tag class="" 这个class=""就是keywords
     * @return Map<String,Integer> start:开始位置 end：结束位置  start<tag><tag/>end
     */
    private static Map<String,Integer> getStartAndEndIndex(String content, String tag, String keyWords){
        int startIndexCount;
        int endIndexCount = 0;
        // 最外面那层tag结束的位置
        int endIndex;
        // 确定标签
        // 开始标签
        String start = "<"+tag;
        // 确定第一次搜寻位置的标签
        String completeStart = start;
        // 结束标签
        String end = "</"+tag+">";
        if (Objects.nonNull(keyWords)){
            completeStart = start+" "+keyWords;
        }
        // 如果内容中已经不包含completeStart或者end了，就不在往下进行，因为这时候说明内容有误，即开始标签和结束标签数量不对应
        if (!content.contains(completeStart) || !content.contains(end)){
            return null;
        }
        // result中用来存放结果
        Map<String,Integer> result = new HashMap<>(2);
        // 之所以为1，是因为上面已经确定了completeStart是存在的，所以说，开始标签数量一定是至少为1的
        startIndexCount = 1;
        // 确定第一次搜寻位置
        int startIndex = content.indexOf(completeStart);
        // 将其截取掉，继续搜寻
        content = content.substring(startIndex+completeStart.length());
        endIndex = completeStart.length()+startIndex;
        // 如果开始标签数量和结束标签数量相同，或者内容中没有start和end，就不在循环
        while (startIndexCount!=endIndexCount && (content.contains(start) || content.contains(end))){
            // 开始、结束标签的位置
            int temStartIndex = content.indexOf(start);
            int temEndIndex = content.indexOf(end);
            // 两个位置相同说明两个都找不到了，跳出循环
            if (temStartIndex == temEndIndex){
                break;
            }
            // 如果没找到结束标签的位置或者开始标签的位置小于结束标签的位置，那么截取掉开始标签
            if (temEndIndex == -1 || temStartIndex < temEndIndex){
                content = content.substring(temStartIndex+start.length());
                // 内容截取的时候，endIndex一定要加截取的长度
                endIndex += temStartIndex+start.length();
                startIndexCount++;
            } else {
                // 结束标签的位置小于开始标签的位置
                content = content.substring(temEndIndex+end.length());
                // 内容截取的时候，endIndex一定要加截取的长度
                endIndex += temEndIndex+end.length();
                endIndexCount++;
            }
        }
        if (startIndex>endIndex){
            return null;
        }
        result.put("start",startIndex);
        result.put("end",endIndex);
        return result;
    }


    /**
     * 获取content中，指定标签范围内的内容
     * @param content vue、html的内容
     * @param tag 标签 如div、html这种
     * @param keyWords tag的限定词，如class=""，这个限定词是紧跟tag后面的内容，如<tag class="" 这个class=""就是keywords
     * @return 符合要求的内容集合
     */
    public static List<String> getContent(String content, String tag, String keyWords){
        List<String> resultList = new ArrayList<>();
        Map<String,Integer> indexMap;
        while ((indexMap = getStartAndEndIndex(content, tag, keyWords)) != null){
            int startIndex = indexMap.get("start");
            int endIndex = indexMap.get("end");
            String result = content.substring(startIndex,endIndex);
            resultList.add(result);
            content = content.substring(endIndex+1);
        }
        return resultList;
    }
}
