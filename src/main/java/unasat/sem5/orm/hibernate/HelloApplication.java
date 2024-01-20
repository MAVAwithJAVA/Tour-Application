package unasat.sem5.orm.hibernate;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    public Map<String, Object> getProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.package", "unasat.sem5.orm.hibernate");
        return properties;
    }
}
