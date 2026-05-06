package com.vin.service;

import com.vin.repo.AppRepository;
import com.vin.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PushService {

    private final AppRepository repo;

    @Value("${app.pull.singleUrl}")
    private String singleUrl;

    @Value("${app.pull.bulkUrl}")
    private String bulkUrl;

    // =========================
    // PUSH SINGLE
    // =========================
    public void pushSingle() {

        Map<String, Object> m = repo.getSingle();

        if (m == null) return;

        process(m);
    }

    // =========================
    // PUSH BATCH
    // =========================
    public void pushBatch() {

        List<Map<String, Object>> list = repo.getBatch(50);

        for (Map<String, Object> m : list) {
            process(m);
        }
    }

    // =========================
    // CORE PROCESS
    // =========================
    private void process(Map<String, Object> m) {

        Long id = ((Number) m.get("id")).longValue();

        try {

            String payload = "{"
                    + "\"reqId\":\"" + m.get("reqId") + "\","
                    + "\"name\":\"" + m.get("name") + "\","
                    + "\"type\":\"" + m.get("type") + "\""
                    + "}";

            String resp = HttpUtil.post(singleUrl, payload);

            if (resp.startsWith("HTTP_2")) {
                repo.update(id, "S", resp);
            } else {
                repo.update(id, "E", resp);
            }

        } catch (Exception e) {

            // 🔥 When 8081 is DOWN → comes here
            repo.update(id, "R", "CONNECTION_FAILED: " + e.getMessage());
        }
    }

    // =========================
    // ETL (CSV ONLY SIMPLE)
    // =========================
    public void etl(org.springframework.web.multipart.MultipartFile file) throws Exception {

        java.io.BufferedReader br =
                new java.io.BufferedReader(new java.io.InputStreamReader(file.getInputStream()));

        String line;
        boolean header = true;

        while ((line = br.readLine()) != null) {

            if (header) {
                header = false;
                continue;
            }

            String[] a = line.split(",");

            repo.insert(a[0], a[1], a[2]);
        }
    }
}