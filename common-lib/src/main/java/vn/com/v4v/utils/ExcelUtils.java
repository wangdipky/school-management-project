package vn.com.v4v.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.util.List;
import java.util.Map;

/**
 * Name: ExcelUtils
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 15/09/2025
 * */
public class ExcelUtils {

    public static <T extends Enum<T>> void exportExcelByEnum(List<?> listData, HttpServletResponse response, String templateName, Class<T> enumClass) throws Exception {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ templateName + ".xlsx";
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

            T[] values = enumClass.getEnumConstants();
            for(T value : values) {
                row.createCell(index).setCellValue(toMap.getOrDefault(toCamelCase(value.toString()), "").toString());
                index++;
            }
        }

        // Export
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
        response.flushBuffer();
    }

    private static String toCamelCase(String str) {

        StringBuilder result = new StringBuilder("");
        if(!str.contains("_")) {

            result.append(str.toLowerCase());
        } else {

            String[] strs = str.split("");
            for(int i = 0; i < strs.length; i++) {

                if(i > 0 && strs[i - 1] != null && strs[i - 1].equals("_")) {

                    result.append(strs[i]);
                } else if (!strs[i].equals("_")) {

                    result.append(strs[i].toLowerCase());
                }
            }
        }
        return result.toString();
    }
}