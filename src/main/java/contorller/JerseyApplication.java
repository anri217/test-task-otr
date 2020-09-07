package contorller;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/services")
public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        packages("src.main.controller");
    }
}
