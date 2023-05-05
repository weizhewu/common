package com.waltz.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: EasyExcel读监听器
 * @createDate: 2023/5/4 17:15
 **/
@Slf4j
public class ReadDataListener<T> extends AnalysisEventListener<T> {
    /**
     * 解析的数据
     */
    List<T> list = new ArrayList<>();

    /**
     * 正文起始行
     */
    private final Integer headRowNumber;

    /**
     * 合并单元格
     */
    private final List<CellExtra> extraMergeInfoList = new ArrayList<>();

    public ReadDataListener(Integer headRowNumber) {
        this.headRowNumber = headRowNumber;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data data
     * @param context context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
    }


    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    public List<T> getData() {
        return list;
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        String s1A1 = "Sheet1!A1";
        String s2A1 = "Sheet2!A1";
        switch (extra.getType()) {
            // 额外信息是批注
            case COMMENT: {
                log.info("额外信息是批注,在rowIndex:{},columnIndex;{},内容是:{}", extra.getRowIndex(), extra.getColumnIndex(),
                        extra.getText());
                break;
            }
            // 超链接
            case HYPERLINK: {
                if (s1A1.equals(extra.getText())) {
                    log.info("额外信息是超链接,在rowIndex:{},columnIndex;{},内容是:{}", extra.getRowIndex(),
                            extra.getColumnIndex(), extra.getText());
                } else if (s2A1.equals(extra.getText())) {
                    log.info(
                            "额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{},"
                                    + "内容是:{}",
                            extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
                            extra.getLastColumnIndex(), extra.getText());
                } else {
                    log.error("Unknown hyperlink!");
                }
                break;
            }
            case MERGE: {
                log.info(
                        "额外信息是合并单元格,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{}",
                        extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
                        extra.getLastColumnIndex());
                if (extra.getRowIndex() >= headRowNumber) {
                    extraMergeInfoList.add(extra);
                }
                break;
            }
            default: {
            }
        }
    }

    public List<CellExtra> getExtraMergeInfoList() {
        return extraMergeInfoList;
    }
}
