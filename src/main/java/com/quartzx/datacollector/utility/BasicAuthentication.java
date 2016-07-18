package com.quartzx.datacollector.utility;

import com.quartzx.datacollector.model.UserData;
import org.glassfish.jersey.internal.util.Base64;


import javax.ws.rs.core.HttpHeaders;
import java.util.StringTokenizer;

/**
 * Created by Rex on 2016/7/18.
 */
public class BasicAuthentication {
    private static final String BASIC = "Basic ";
    private static final String AUTHORIZATION = "Authorization";

    public static UserData getUserDate(HttpHeaders headers) {
        UserData user = new UserData();

        String header = headers.getHeaderString(AUTHORIZATION);

        if (header.startsWith(BASIC)) {
            String encoded = header.substring(BASIC.length());
            if (encoded.length() == 0) {
                return user;
            } else {
                String plain = Base64.decodeAsString(encoded);
                String[] tokens = plain.split(":");
                user.setUsername(tokens[0]);
                user.setPassword(tokens[1]);
                return user;
            }
        } else {
            return user;
        }
    }
}
