# movies
Personal movies collection management system

This project was created with JBoss Seam 2.1 and recently ported to Seam 2.3

So the ear file it is now deployable on JBoss EAP 6.4 java application server.

## Building instructions
Edit the file build.properties and set the jboss.home directory making it point to your jboss eap 6 home directory or your jboss community 7 home directory 

Edit the file jboss_home/standalone/configuration/standalone.xml (or the one suitable for your JBoss configuration) and add this lines to the datasources section:

```
                <datasource jndi-name="java:/moviesDatasource" pool-name="moviesDatasource" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:../standalone/deployments/movies;INIT=CREATE SCHEMA IF NOT EXISTS MOVIES</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
```
In the example above the I use the h2 db file, located in jboss_home/standalone/deployments, but of course you can place it where you prefer, or even use another db engine as yuo like.

You can then build and deploy the project with the command 
```
ant deploy
```
or, to deploy it as an exploded archive:
```
ant explode; touch $JBOSS_HOME/standalone/deployments/movies.ear.dodeploy
```

