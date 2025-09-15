package vn.com.v4v.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.util.List;
import java.util.Map;

/**
 * Name: ExcelUtils
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 15/09/2025
 * */
@Component
public class ExcelUtils {

    public static void exportExcelByEnum(List<?> listData, HttpServletResponse response, String templateName) throws Exception {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // Init data
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);


        XSSFWorkbook workbook = new XSSFWorkbook(ResourceUtils.getFile("classpath:template/excel/"+ templateName +".xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int indexRow = 4;

        for(Object item : listData) {

            XSSFRow row = sheet.createRow(indexRow++);

            // Init row from ENUM
            String toJson = gson.toJson(item);
            Map<String, Object> toMap = mapper.readValue(toJson, Map.class);
            int index = 0;
            for(ExportGroupExcelEnum excelEnum : ExportGroupExcelEnum.values()) {

                row.createCell(index).setCellValue(toMap.getOrDefault(excelEnum.getKey(), "null").toString());
                index++;
            }
            int a = 1;
        }

        // Export
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
        response.flushBuffer();
    }
}
