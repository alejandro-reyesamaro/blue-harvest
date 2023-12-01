package com.harvest.testools.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTools {

    public static String writeObjectAsJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
