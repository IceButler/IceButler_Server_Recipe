package smile.iceBulterrecipe.global.utils;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import smile.iceBulterrecipe.user.exception.TokenExpirationException;

import java.util.Date;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenUtils {
  public static final String USER_IDX = "userIdx";
  public static final String ONE_BLOCK = " ";


  public static String secretKey;
  public static String tokenType;
  @Value("${jwt.secret}")
  public void setSecretKey(String value) {
    secretKey = value;
  }

  @Value("${jwt.token-type}")
  public void setTokenType(String value) {
    tokenType = value;
  }


  public boolean isValidToken(String justToken) {
    if (justToken != null && justToken.split(ONE_BLOCK).length == 2)
      justToken = justToken.split(ONE_BLOCK)[1];
    try {
      Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(justToken).getBody();
      return true;
    } catch (ExpiredJwtException exception) {
      log.error("Token Tampered");
      return true;
    } catch (MalformedJwtException exception) {
      log.error("Token MalformedJwtException");
      return false;
    } catch (ClaimJwtException exception) {
      log.error("Token ClaimJwtException");
      return false;
    } catch (UnsupportedJwtException exception) {
      log.error("Token UnsupportedJwtException");
      return false;
    } catch (CompressionException exception) {
      log.error("Token CompressionException");
      return false;
    } catch (RequiredTypeException exception) {
      log.error("Token RequiredTypeException");
      return false;
    } catch (NullPointerException exception) {
      log.error("Token is null");
      return false;
    } catch (Exception exception) {
      log.error("Undefined ERROR");
      return false;
    }
  }

  private Claims getJwtBodyFromJustToken(String justToken) {
    try {
      return Jwts.parser()
              .setSigningKey(secretKey)
              .parseClaimsJws(justToken)
              .getBody();
    } catch (ExpiredJwtException e) {
      throw new TokenExpirationException();
    }
  }
  public String getUserIdFromFullToken(String fullToken) {
    return String.valueOf(getJwtBodyFromJustToken(parseJustTokenFromFullToken(fullToken)).get(USER_IDX));
  }

  // "Bearer eyi35..." 로 부터 "Bearer " 이하만 떼어내는 메서드
  public String parseJustTokenFromFullToken(String fullToken) {
    if (StringUtils.hasText(fullToken)
            &&
            fullToken.startsWith(Objects.requireNonNull(tokenType))
    )
      return fullToken.split(ONE_BLOCK)[1]; // e부터 시작하는 jwt 토큰
    return null;
  }

}
