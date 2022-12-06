package org.kenshin.gatewaypredicates.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.redisExpiration}")
    private Long redisExpiration;

    @Value("${jwt.redisRenewal}")
    private Long redisRenewal;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取JWT中的负载(失效仍然返回)
     */
    public Claims getClaimsFromTokenRegardlessDate(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUserNameFromToken(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        if (expiredDate == null) {
            return true;
        }else {
            return expiredDate.before(new Date());
        }
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }else {
            return claims.getExpiration();
        }
    }

    /**
     * 根据用户信息生成token
     */
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
//    public String refreshHeadToken(String oldToken) {
//        if(StrUtil.isEmpty(oldToken)){
//            return null;
//        }
//        String token = oldToken.substring(tokenHead.length());
//        if(StrUtil.isEmpty(token)){
//            return null;
//        }
//        //token校验不通过
//        Claims claims = getClaimsFromToken(token);
//        if(claims==null){
//            return null;
//        }
//        //如果token已经过期，不支持刷新
//        if(isTokenExpired(token)){
//            return null;
//        }
//        //如果token在30分钟之内刚刷新过，返回原token
//        if(tokenRefreshJustBefore(token,30*60)){
//            return token;
//        }else{
//            claims.put(CLAIM_KEY_CREATED, new Date());
//            return generateToken(claims);
//        }
//    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
//    private boolean tokenRefreshJustBefore(String token, int time) {
//        Claims claims = getClaimsFromToken(token);
//        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
//        Date refreshDate = new Date();
//        //刷新时间在创建时间的指定时间内
//        if(refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time))){
//            return true;
//        }
//        return false;
//    }

//    public String getToken(HttpServletRequest request) {
//        final String requestHeader = request.getHeader(tokenHeader);
//        if (requestHeader != null && requestHeader.startsWith(tokenHead)) {
//            return requestHeader.substring(7);
//        }
//        return null;
//    }

    /**
     * @param token 需要检查的token
     */
//    public void checkRenewal(String token) {
//        // 判断是否续期token,计算token的过期时间
//        Set<String> keys = redisService.getKeysByPattern("*" + token);
//        if (keys == null || keys.isEmpty()){
//            return;
//        }
//        String key = keys.iterator().next();
//////        long time = redisService.getExpire("online-" + token) * 1000;
////        long time = redisService.getExpire(key) * 1000;
////        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
////        // 判断当前时间与过期时间的时间差
////        long differ = expireDate.getTime() - System.currentTimeMillis();
////        // 如果在续期检查的范围内，则续期
////        if (differ <= 1800000) {
////            long renew = time/1000 + 3600;
//////            redisService.expire("online-" + token, renew);
////            redisService.expire(key, renew);
////        }
//        if (redisService.getExpire(key) <= redisRenewal){
//            redisService.expire(key, redisExpiration);
//        }
//    }
//
//    public long getValidTime(HttpServletRequest request){
//        String token = this.getToken(request);
//        return 0;
//    }
}