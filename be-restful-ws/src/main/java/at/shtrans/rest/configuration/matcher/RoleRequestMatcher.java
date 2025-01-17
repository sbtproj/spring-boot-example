package at.shtrans.rest.configuration.matcher;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.List;

public class RoleRequestMatcher implements RequestMatcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoleRequestMatcher.class);

    private String expectedRequestURI;
    private List<String> expectedRoles;

    public RoleRequestMatcher(String expectedRequestURI, String... expectedRoles) {
        this.expectedRequestURI = expectedRequestURI;
        this.expectedRoles = Arrays.asList(expectedRoles);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        LOGGER.info("BEGIN : matches -> request={}", request);

        boolean match = false;

        LOGGER.info("INFO : matches -> request.getParameter(role)={}", request.getParameter("role"));
        LOGGER.info("INFO : matches -> request.getRequestURI()={}", request.getRequestURI());

        if (StringUtils.isNotBlank(request.getRequestURI())
                && StringUtils.isNotBlank(request.getParameter("role"))) {

            match = like(expectedRequestURI, request.getRequestURI()) && expectedRoles.contains(request.getParameter("role"));
        }

        LOGGER.info("END : matches -> match={}", match);
        return match;
    }

    private boolean like(String expectedRequestURI, String currentRequestURI){
        LOGGER.info("BEGIN : like -> expectedRequestURI={}, currentRequestURI={}", expectedRequestURI, currentRequestURI);

        if(currentRequestURI.contains("/")) {
            currentRequestURI = currentRequestURI.substring(0, currentRequestURI.lastIndexOf("/"));
        }

        boolean like = expectedRequestURI.equals(currentRequestURI);

        LOGGER.info("INFO : like -> like={}", like);
        return like;
    }
}
