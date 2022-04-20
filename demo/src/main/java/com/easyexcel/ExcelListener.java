package com.easyexcel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 *
 * @author lurunze
 * @date 2019/04/22
 */
public class ExcelListener extends AnalysisEventListener {
    /**
     * 自定义用于暂时存储data。
     * 可以通过实例获取该值
     */
    private List<Object> datas = new ArrayList<>();
    private List<List<String>> excelDatas = new ArrayList<>();


    @Override
    public void invoke(Object object, AnalysisContext context) {
        datas.add(object);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    public List<List<String>> getDatas() {
        for (Object object : datas) {
            excelDatas.add((List<String>) object);
        }
        return excelDatas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}