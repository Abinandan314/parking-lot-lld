package com.parkinglot.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomInputHandler {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Integer getvalidInteger() throws IOException {
        String input = reader.readLine();
        return Integer.parseInt(input);
    }

    public static String getValidString() throws IOException {
        return reader.readLine();
    }
}
