package com.endava.catalog.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

	private static Properties properties = new Properties();

	static {
		final InputStream inputStream = EnvReader.class.getClassLoader().getResourceAsStream( "local.properties" );
		try {
			properties.load( inputStream );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public static String getBaseUri() {
		return properties.getProperty( "base.uri" );
	}

	public static Integer getPort() {
		return Integer.parseInt( properties.getProperty( "port" ) );
	}

	public static String getBasePath() {
		return properties.getProperty( "base.path" );
	}
}
