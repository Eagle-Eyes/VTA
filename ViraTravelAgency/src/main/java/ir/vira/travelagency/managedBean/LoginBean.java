package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@RequestScoped
@ManagedBean
@Getter
@Setter
public class LoginBean extends BaseBean {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);
    
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    
    private String username;
    private String password;
    
    public LoginBean() {
        logger.warn("Login Bean");
    }
    
    public void authenticate() {
        logger.warn("Login Bean authenticate: " + username + ", password: " + password);
        
        Authentication auth;
        try {
            logger.warn("Auth1");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            logger.warn("Auth2");
            auth = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);
            logger.warn("Auth3");
            
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.warn("Auth4");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
            logger.warn("Redirecting ...");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "Welcome to home..."));
            
        } catch (BadCredentialsException bce) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", bce.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
            e.printStackTrace();
        }
    }
    
    public void info(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
    
}
