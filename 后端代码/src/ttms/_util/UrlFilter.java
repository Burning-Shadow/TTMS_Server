package ttms._util;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlFilter implements Filter {  
	   
    public final static String DEFAULT_URI_ENCODE = "UTF-8";  
       
    private FilterConfig config = null;  
    private String encode = null;
    @Override
	public void init(FilterConfig config) throws ServletException {
    	this.config = config;  
        this.encode = config.getInitParameter("DEFAULT_URI_ENCODE");  
        if(this.encode == null) {  
            this.encode = DEFAULT_URI_ENCODE;  
        }  
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, javax.servlet.FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        String ch = URLDecoder.decode(uri, encode);  
        chain.doFilter(request, response);
        if(uri.equals(ch)) {  
            chain.doFilter(request, response);  
            return;  
        }  
        ch = ch.substring(request.getContextPath().length());  
        config.getServletContext().getRequestDispatcher(ch).forward(req, resp);
	}
	@Override
	public void destroy() {
		config = null;
	}
}  
