package org.sid;



import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "customer.params")
public record ConfigParams(int p1,int p7) {

}
