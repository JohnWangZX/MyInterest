package 安全机制;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author: wzx
 * @date: 2023/5/26
 */
public class JWTOperation {
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
    // 签名密钥
    private static final String secret = getRandomString(10);

    public static void main(String[] args) {
        System.out.println( sign("13704400822"));
    }
    public static String sign(String uuid) {
        try {
//            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // 附带phone信息
            return JWT.create()
                    .withClaim("uuid", uuid)
//                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }
    public static String getUuid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uuid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
