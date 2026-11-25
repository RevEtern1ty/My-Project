package eu.itcrafters.myproject.infrastructure.db;

import org.hsqldb.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "demo.hsql-server.enabled", havingValue = "true")
public class HsqlServerConfig {
    private static final Logger log = LoggerFactory.getLogger(HsqlServerConfig.class);

    /**
     * Gives local database tools access to the same in-memory database as the API.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server hsqlServer() {
        Server server = new Server();
        server.setLogWriter(null);
        server.setSilent(true);
        server.setDatabaseName(0, "mydb");
        server.setDatabasePath(0, "mem:mydb");
        server.setAddress("127.0.0.1");
        server.setPort(9001);
        log.info("Starting local HSQLDB server at 127.0.0.1:9001 (in-memory database 'mydb')");
        return server;
    }
}
