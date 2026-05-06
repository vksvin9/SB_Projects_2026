package com.vin.util;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.vin.model.AppDataRequest;

public class CsvUtil {

    public static List<AppDataRequest> parse(MultipartFile file) throws Exception {

        List<AppDataRequest> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] row;
            boolean header = true;

            while ((row = reader.readNext()) != null) {

                if (header) { header = false; continue; }

                AppDataRequest r = new AppDataRequest();
                r.setReqId(row[0]);
                r.setName(row[1]);
                r.setType(row[2]);

                list.add(r);
            }
        }
        return list;
    }
}