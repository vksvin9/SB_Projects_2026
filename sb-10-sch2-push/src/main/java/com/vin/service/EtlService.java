package com.vin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vin.model.AppDataRequest;
import com.vin.repo.AppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtlService {

    private final AppRepository repo;

    public void process(MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();

            if (fileName == null) {
                throw new RuntimeException("Invalid file");
            }

            List<AppDataRequest> list = new ArrayList<>();

            // ================= CSV =================
            if (fileName.endsWith(".csv")) {

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(file.getInputStream())
                );

                String line;
                boolean header = true;

                while ((line = br.readLine()) != null) {

                    if (header) {
                        header = false;
                        continue;
                    }

                    String[] arr = line.split(",");

                    if (arr.length < 3) continue;

                    AppDataRequest r = new AppDataRequest();
                    r.setReqId(arr[0].trim());
                    r.setName(arr[1].trim());
                    r.setType(arr[2].trim());

                    list.add(r);
                }
            }

            // ================= EXCEL =================
            else if (fileName.endsWith(".xlsx")) {

                var workbook = WorkbookFactory.create(file.getInputStream());
                var sheet = workbook.getSheetAt(0);

                boolean header = true;

                for (var row : sheet) {

                    if (header) {
                        header = false;
                        continue;
                    }

                    AppDataRequest r = new AppDataRequest();

                    r.setReqId(row.getCell(0).getStringCellValue());
                    r.setName(row.getCell(1).getStringCellValue());
                    r.setType(row.getCell(2).getStringCellValue());

                    list.add(r);
                }
            }

            else {
                throw new RuntimeException("Only CSV/XLSX supported");
            }

            // ================= INSERT =================
            repo.insertBatch(list);

        } catch (Exception e) {
            throw new RuntimeException("ETL failed: " + e.getMessage());
        }
    }
    
    public List<Map<String, Object>> getLatest(int limit) {
        return repo.fetchLatest(limit);
    }
}