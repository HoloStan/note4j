package me.holostan.note4j.core.util.template.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ghu on 6/19/2017.
 */
@Component
@ConfigurationProperties(prefix = "rest.proxy")
public class ProxyConfig {

    private Boolean enabled;
    private String host;
    private Integer port;

    //region getter & setter
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    //endregion
}
