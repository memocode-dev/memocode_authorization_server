package dev.memocode.memocode_authorization_server.in.api.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.UUID;

@Profile("!test")
@Configuration
@RequiredArgsConstructor
public class JWKConfig {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String RSA_KEY_PAIR = "rsaKeyPair";

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = loadOrCreateRsaKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private RSAKey loadOrCreateRsaKey() {
        // Redis에서 RSA 키 쌍을 조회
        String rsaKeyJson = stringRedisTemplate.opsForValue().get(RSA_KEY_PAIR);
        if (rsaKeyJson != null) {
            try {
                // Redis에 저장된 키가 있다면, 이를 로드하여 사용
                RSAKey rsaKey = RSAKey.parse(rsaKeyJson);
                return rsaKey;
            } catch (ParseException e) {
                throw new IllegalStateException("Failed to parse RSA Key from Redis", e);
            }
        }

        // 키가 없다면 새로운 키를 생성
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        // 새로 생성된 RSA 키 쌍을 Redis에 저장
        stringRedisTemplate.opsForValue().set(RSA_KEY_PAIR, rsaKey.toJSONString());

        return rsaKey;
    }

    private static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Failed to generate RSA Key", ex);
        }
    }
}
