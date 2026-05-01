package com.vin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExternalService {

    public String getPosts() throws Exception {

        log.info("EXT CALL -> GET posts");

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        return br.lines().collect(Collectors.joining());
    }
}