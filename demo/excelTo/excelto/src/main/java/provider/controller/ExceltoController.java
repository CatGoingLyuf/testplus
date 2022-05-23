package provider.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import provider.utils.ExcelListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ExceltoController {

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        EasyExcel.read(file.getInputStream(), excelListener).sheet().doRead();
        // 读取表格头数据
        Map<String, Integer> importHeads = excelListener.getImportHeads();
        // 读取数据集合
        List<JSONObject> dataList = excelListener.getDataList();
        return null;
    }
}
