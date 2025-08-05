package com.example.spring_security_asymetric_encrytion.security;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {
    private KeyUtils() {}
    public static PrivateKey loadPrivateKey(final String pemPath) throws Exception {
        final String key=readKeyFromResource(pemPath)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\\s+", "");
        final byte[] decodedKey = Base64.getDecoder().decode(key);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public static PublicKey loadPublicKey(final String pemPath) throws Exception {
        final String key=readKeyFromResource(pemPath)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\\s+", "");
        final byte[] decodedKey = Base64.getDecoder().decode(key);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private static String readKeyFromResource(String pemPath) throws Exception {
        try (final InputStream inputStream = KeyUtils.class.getResourceAsStream(pemPath)) {
            if(inputStream == null) {
                throw new IllegalArgumentException("Could not find key file " + pemPath);
            }
            return new String(inputStream.readAllBytes());

        }
    }
}
