package com.beiming.juc.jwt;

import com.beiming.juc.web.dao.CarMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;

public class JwtTest {

  @Resource
  CarMapper carMapper;

  public static void main(String[] args) {
    Key key = new SecretKeySpec("javastack1".getBytes(),
        SignatureAlgorithm.HS256.getJcaName());
    Map<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("type", "1");
    String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
    String compactJws = Jwts.builder().setHeader(stringObjectMap)
        .setPayload(payload).signWith(SignatureAlgorithm.HS512, key).compact();

    System.out.println("jwt key:" + new String(key.getEncoded()));
    System.out.println("jwt payload:" + payload);
    System.out.println("jwt encoded:" + compactJws);

    System.out.println("---------------------------------------------");
    Key key1 = new SecretKeySpec("javastack1".getBytes(),
        SignatureAlgorithm.HS256.getJcaName());
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key1).parseClaimsJws(compactJws);
    JwsHeader header = claimsJws.getHeader();
    Claims body = claimsJws.getBody();

    System.out.println("jwt header:" + header);
    System.out.println("jwt body:" + body);
    System.out.println("jwt body user-id:" + body.get("user_id", String.class));
    String sr = new String(TextCodec.BASE64URL
        .decode("eyJ1c2VyX2lkIjoiMTM0MTEzNyIsICJleHBpcmVfdGltZSI6IjIwMTgtMDEtMDEgMDowMDowMCJ9"));
    System.out.println(sr);
  }

}
