package com.cibertec.QuickSale.model;

public class CodeGenerator {
    private static int count = 1;


    public static String generateCode() {
        String code = String.format("0000%d", count);

        count++;
        return code;
    }
}
