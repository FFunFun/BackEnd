package com.ffuntree.ffunfun.security;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class PasswordGenerator {

    private final Random random = new Random();

    public String generateRandomPassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(3);
            switch (index) {
                case 0:
                    sb.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    sb.append((char) (random.nextInt(26) + 65));
                    break;
                case 2:
                    sb.append(random.nextInt(10));
                    break;
            }
        }
        return sb.toString();
    }

}
