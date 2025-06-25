package com.bindeshwar.bindeshwarmart.application.config;

import java.util.Base64;

public class Ecryption {
	
	public static String encode(byte[] data) {
        byte[] encodedBytes = Base64.getEncoder().encode(data);
        return new String(encodedBytes);
    }

    public static byte[] decode(String encodedData) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        return decodedBytes;
    }
	
    public static void main(String[] args) {
        String originalData = "MTExMQ==";
        byte[] originalBytes = originalData.getBytes();

        // Encoding
        String encodedData = encode(originalBytes);
        System.out.println("Encoded data: " + encodedData);

        
        // Decoding
        byte[] decodedBytes = decode(originalData);
        String decodedData = new String(decodedBytes);
        System.out.println("Decoded data: " + decodedData);
    }
}


