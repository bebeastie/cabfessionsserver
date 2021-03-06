dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
	
}

hibernate {
	cache.use_second_level_cache=true
	cache.use_query_cache=true
	cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			//			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			//			url = "jdbc:hsqldb:mem:devDB"
			driverClassName = "com.mysql.jdbc.Driver"
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/cabfessions"
			username =  "grails"
			password = "groovy"
//			loggingSql = true  
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			//			dialect=org.hibernate.dialect.MySQLDialect.class
			driverClassName = "com.mysql.jdbc.Driver"
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/cabfessions"
			username =  "grails"
			password = "groovy"
		}
	}
}