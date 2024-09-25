package connection;

import java.util.Properties;

public class ConnectionProperties {
	
	public static Properties getConnection() {
		Properties props = new Properties();
		props.put("user", "RM97722");
		props.put("password", "120205");
		return props;
		
	}
}
