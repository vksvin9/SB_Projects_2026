package com.vin.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.vin.entity.User;

@Component
public class CsvHelper {

    public List<User> parseCsv(InputStream is) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> rows = reader.readAll();
            List<User> users = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);

                User user = new User();
                user.setName(row[0]);
                user.setEmail(row[1]);
                user.setAge(Integer.parseInt(row[2]));

                users.add(user);
            }

            return users;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream toCsv(List<User> users) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(out))) {

            writer.writeNext(new String[]{"Name", "Email", "Age"});

            for (User u : users) {
                writer.writeNext(new String[]{
                        u.getName(),
                        u.getEmail(),
                        String.valueOf(u.getAge())
                });
            }

            writer.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}