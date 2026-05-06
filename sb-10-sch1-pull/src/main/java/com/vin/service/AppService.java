package com.vin.service;

import com.vin.model.AppDataRequest;
import com.vin.model.AppDataResponse;
import com.vin.repo.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository repo;

    public void processSingle(AppDataRequest req) {
        validate(req);
        repo.insert(req);
    }

    public void processBulk(List<AppDataRequest> list) {

        if (list.size() > 50) {
            throw new RuntimeException("Max 50 records allowed");
        }

        list.forEach(this::validate);
        repo.insertBatch(list);
    }

    public List<AppDataResponse> fetch(int limit, int offset) {
        return repo.fetch(limit, offset);
    }

    private void validate(AppDataRequest req) {
        if (!req.getType().matches("single|multi")) {
            throw new RuntimeException("Invalid type");
        }
    }
}