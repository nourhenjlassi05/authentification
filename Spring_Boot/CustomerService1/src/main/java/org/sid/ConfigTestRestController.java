package org.sid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RefreshScope
public class ConfigTestRestController {
	@Autowired
	private ConfigParams configParams;
 
	@GetMapping("/config1")
	public ConfigParams params2() {
		return configParams;
	}
}