package com.qaengineer.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataLoader {

    // ObjectMapper es la clase de Jackson que lee JSON
    private static final ObjectMapper mapper = new ObjectMapper();

    // Ruta al archivo de datos
    private static final String DATA_PATH = "test-data/users.json";

    public static UserData getValidUser() {
        try {
            JsonNode root = mapper.readTree(new File(DATA_PATH));
            // Lee el nodo "validUser" del JSON
            return mapper.treeToValue(root.get("validUser"), UserData.class);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo users.json: " + e.getMessage());
        }
    }

    public static Object[][] getInvalidUsers() {
        try {
            JsonNode root = mapper.readTree(new File(DATA_PATH));
            JsonNode invalidUsers = root.get("invalidUsers");

            // Convierte el array JSON en Object[][] para @DataProvider
            List<Object[]> data = new ArrayList<>();
            for (JsonNode user : invalidUsers) {
                data.add(new Object[]{
                        user.get("username").asText(),
                        user.get("password").asText(),
                        user.get("expectedError").asText()
                });
            }
            return data.toArray(new Object[0][]);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo users.json: " + e.getMessage());
        }
    }
}