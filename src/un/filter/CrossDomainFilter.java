package un.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author
 * @date
 */
public class CrossDomainFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(CrossDomainFilter.class);

    private Properties pp = new Properties();

    private FilterConfig config;

    private String allowDomain = "*";

    @Override
    public void destroy() {
        pp = null;
        this.config = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 得到请求的uri和url
        String reqUri = req.getRequestURI();
        String reqUrl = req.getRequestURL().toString();
        log.info(" reqUri:{} ,reqUrl:{} ", reqUri, reqUrl);

        // 跨域处理
        this.crossDomain(req, resp, reqUrl);
        chain.doFilter(req, resp);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * 处理跨域问题
     */
    private void crossDomain(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        log.info("REFERER:{}", request.getHeader("REFERER"));
//        String referer = request.getHeader("REFERER");
//        String reqRefererDomain = "";
//        if (referer != null && referer.length() > referer.indexOf(".com") + 4) {
//            reqRefererDomain = referer.substring(0, referer.indexOf(".com") + 4);
//            log.info("reqRefererDomain:{}", reqRefererDomain);
//            if (reqRefererDomain.equals(allowDomain)) {
//                response.setHeader("Access-Control-Allow-Origin", allowDomain );
//            }
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

    }

}