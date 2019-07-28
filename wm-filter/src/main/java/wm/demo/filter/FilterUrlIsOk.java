package wm.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/***
 * 检查url请求是否正确，，防止出现csrf漏洞
 * @author think
 *
 */


@Component
public class FilterUrlIsOk implements Filter {

    private final Logger logger = LoggerFactory.getLogger(FilterUrlIsOk.class);

    @Autowired
    FilterUrlConfig f;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        logger.info("初始化filter过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
  /*      //验证是否是可信url地址
        HttpServletRequest re = (HttpServletRequest) request;

        String[] urls = f.getUrls();

        //使用Referer判断内容
        Boolean refIsOk = false;

        //获取referer
        String referer = re.getHeader("Referer");

        logger.info("referer:" + referer);

        //判断是否是可信地址
        if (referer != null) {

            for (int i = 0; i < urls.length; i++) {

                refIsOk = referer.startsWith(urls[i]);
                if (refIsOk)
                    break;
            }
        }

         查看配置uri放行
        if (!refIsOk && (re.getRequestURI().endsWith("/cc") || re.getRequestURI().contains("/actuator")))
            refIsOk = true;


        //通过判断referer方式
        if (refIsOk) {
            //放行
            chain.doFilter(request, response);

        } else {
            //跳转至错误界面
            logger.info("不识别的请求地址：");
            request.getRequestDispatcher("error").forward(request, response);
        }
        */
    	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        logger.info("filter过滤器已经销毁");
    }


}
