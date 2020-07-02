## prepare

The client application name needs to be the same as the properties name in the repository. For example, your config client application name is config-client, then your properties file in your repository should be config-client-dev.properties. Or you will get the "Could not resolve placeholder ${xxx}" error.

### Locating Remote Configuration Resources
The Config Service serves property sources from /{application}/{profile}/{label}, where the default bindings in the client app are as follows:

"name" = ${spring.application.name}

"profile" = ${spring.profiles.active} (actually Environment.getActiveProfiles())

"label" = "master"

## test

	curl http://192.168.1.13:8081/${appname}/local
	
	curl http://192.168.1.13:8081/${appname}/k8s
