package bupt.dawsonlee1790.sop.components;

import bupt.dawsonlee1790.sop.certification.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class User {

    @Autowired
    private HttpServletRequest httpServletRequest;

    private ObjectMapper objectMapper = new ObjectMapper();


    private Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }

    public List<Role> getGroupList() throws IOException {
        String jwtToken = httpServletRequest.getHeader(AUTHORIZATION);
        Claims claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwtToken).getBody();
        List<String> text = claims.get("Role", ArrayList.class);
        List<Role> roles = new ArrayList<>();
        text.forEach(it -> {
            if (it.equals(Role.Forklift.name())) roles.add(Role.Forklift);
            else if (it.equals(Role.WorkshopManager.name())) roles.add(Role.WorkshopManager);
            else if (it.equals(Role.Researcher.name())) roles.add(Role.Researcher);
            else if (it.equals(Role.Planner.name())) roles.add(Role.Planner);
            else if (it.equals(Role.ProductionLeader.name())) roles.add(Role.ProductionLeader);
        });
        return roles;
//        return objectMapper.readValue(text.toString(), new TypeReference<List<Role>>() {});
    }

    public String getUserName() {
        String jwtToken = httpServletRequest.getHeader(AUTHORIZATION);
        Claims claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwtToken).getBody();
        return claims.get("UserName", String.class);
    }

}
