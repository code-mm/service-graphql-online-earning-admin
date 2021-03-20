package com.shuangyangad.service.admin.utils;

import com.shuangyangad.service.admin.exception.AppException;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenUtils {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");

    private static final String ROLE = "role";
    private static final String USER_ID = "user_id";

    public static void main(String[] args) {


//        String token = "Bearer 1111";
//
//        Matcher matcher = BEARER_PATTERN.matcher(token);
//
//        boolean b = matcher.find();
//
//        System.out.println(b);
//        String group = matcher.group(1);
//        System.out.println(group);

        String token  ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzeS10b2tlbiIsImlhdCI6MTYxMzM3NzI4NSwiZXhwIjoxNjE1OTY5Mjg1LCJ1c2VyX2lkIjoiMWNjMjgzNmM0MDNhNWZkNjg5YWMxZjVhMDZmZjljY2YiLCJyb2xlIjoic3lzdGVtX3VzZXIifQ.JYvYC2mB0I4temym10003XJUsQn7utKGc5S0nNMelCs";


        boolean b = checkToken(token);

        System.out.println(b);

        String role = getRole(token);
        System.out.println(role);

        String userId = getUserId(token);
        System.out.println(userId);


    }


    /**
     * token过期时间
     */
    public static final long EXPIRE = 1000L * 60L * 60L * 24L * 30L;
    /**
     * 秘钥
     */
    public static final String APP_SECRET = "1e5d53b52dd944eef7ebab97fd9ae173";

    public static String generateToken(String userId, String role) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("sy-token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim(USER_ID, userId)  //设置token主体部分 ，存储用户信息
                .claim(ROLE, role)  //设置token主体部分 ，存储用户信息
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }


    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (org.springframework.util.StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token 获取用户id
     *
     * @return
     */
    public static String getUserId(String token) {
        if (token == null) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get(USER_ID);
    }

    public static String getRole(String token) {
        if (token == null) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get(ROLE);
    }


    public static void checkAuthorization(DataFetchingEnvironment environment) {
        DefaultGraphQLServletContext context = environment.getContext();
        HttpServletRequest httpServletRequest = context.getHttpServletRequest();
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null || "".equals(authorization)) {
            throw new AppException(AppException.MESSAGE.NO_AUTHORIZATION);
        }
        Matcher matcher = BEARER_PATTERN.matcher(authorization);
        if (!matcher.find()) {
            throw new AppException(AppException.MESSAGE.NO_AUTHORIZATION);
        }
        String token = matcher.group(1);
        if (!checkToken(token)) {
            throw new AppException(AppException.MESSAGE.NO_AUTHORIZATION);
        }
    }
}
