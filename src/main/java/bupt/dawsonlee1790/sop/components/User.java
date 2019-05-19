package bupt.dawsonlee1790.sop.components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class User {

    @Autowired
    private HttpServletRequest httpServletRequest;

    private ObjectMapper objectMapper;


    private Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }

    public List<String> getGroupList() throws IOException {
        String jwtToken = httpServletRequest.getHeader(AUTHORIZATION);
        Claims claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJwt(jwtToken).getBody();
        String text = claims.get("Role", String.class);
        return objectMapper.readValue(text, new TypeReference<List<String>>(){});
    }

    public String getUserName() {
        String jwtToken = httpServletRequest.getHeader(AUTHORIZATION);
        Claims claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJwt(jwtToken).getBody();
        return claims.get("UserName", String.class);
    }

}
