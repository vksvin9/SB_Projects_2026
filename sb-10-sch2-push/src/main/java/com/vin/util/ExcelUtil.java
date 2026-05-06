package com.vin.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.vin.model.AppDataRequest;

public class ExcelUtil {

    public static List<AppDataRequest> parse(MultipartFile file) throws Exception {

        List<AppDataRequest> list = new ArrayList<>();

        Workbook wb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = wb.getSheetAt(0);

        boolean header = true;

        for (Row row : sheet) {

            if (header) { header = false; continue; }

            AppDataRequest r = new AppDataRequest();

            r.setReqId(row.getCell(0).getStringCellValue());
            r.setName(row.getCell(1).getStringCellValue());
            r.setType(row.getCell(2).getStringCellValue());

            list.add(r);
        }

        return list;
    }
}