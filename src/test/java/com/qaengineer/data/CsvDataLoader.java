package com.qaengineer.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLoader {

    private static final String CSV_PATH = "test-data/users.csv";

    public static Object[][] getInvalidUsersFromCsv() {
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Salta la primera línea (cabecera)
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Divide la línea por comas
                String[] values = line.split(",", -1);
                // -1 = incluye campos vacíos al final
                // Sin -1, un campo vacío al final se ignora

                if (values.length >= 3) {
                    data.add(new Object[]{
                            values[0].trim(), // username
                            values[1].trim(), // password
                            values[2].trim()  // expectedError
                    });
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo users.csv: " + e.getMessage());
        }

        return data.toArray(new Object[0][]);
    }
}