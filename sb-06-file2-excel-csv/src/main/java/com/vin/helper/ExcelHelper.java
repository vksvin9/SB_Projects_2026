package com.vin.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.vin.entity.User;

@Component
public class ExcelHelper {

    public List<User> parseExcel(InputStream is) {
        try (Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<User> list = new ArrayList<>();

            Iterator<Row> rows = sheet.iterator();
            rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();

                User u = new User();
                u.setName(row.getCell(0).getStringCellValue());
                u.setEmail(row.getCell(1).getStringCellValue());
                u.setAge((int) row.getCell(2).getNumericCellValue());

                list.add(u);
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream toExcel(List<User> users) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Users");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Age");

            int i = 1;
            for (User u : users) {
                Row row = sheet.createRow(i++);
                row.createCell(0).setCellValue(u.getName());
                row.createCell(1).setCellValue(u.getEmail());
                row.createCell(2).setCellValue(u.getAge());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}