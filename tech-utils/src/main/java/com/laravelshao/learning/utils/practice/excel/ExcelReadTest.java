package com.laravelshao.learning.utils.practice.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @author qinghua.shao
 * @date 2019/11/12
 * @since 1.0.0
 */
public class ExcelReadTest {

    public static void main(String[] args) {
        String fileName = "/Users/sylvia/Desktop/demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

    }
}
