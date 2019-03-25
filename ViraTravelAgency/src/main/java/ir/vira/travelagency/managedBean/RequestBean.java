package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ViewScoped
@ManagedBean
@Getter
@Setter
public class RequestBean extends BaseBean {
    
    private static final Logger logger = LoggerFactory.getLogger(RequestBean.class);
    
    private Map<String, String> requestParams;
    
    private HttpServletRequest request;
    
    public RequestBean(HttpServletRequest request) {
        this.request = request;
        
        logger.warn("Request Bean");
        
        requestParams = new HashMap<>();
        while (request.getParameterNames().hasMoreElements()) {
            String s = request.getParameterNames().nextElement();
            requestParams.put(s, request.getParameter(s));
        }
    }
    
}
