package com.todo.app.config;

import com.couchbase.client.java.env.ClusterEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Configuration
@EnableCouchbaseRepositories
@Slf4j
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${todo.cb.connectionstring}")
    private String connectionString;
    @Value("${todo.cb.username}")
    private String username;
    @Value("${todo.cb.password}")
    private String password;
    @Value("${todo.cb.bucketname}")
    private String bucketName;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Override
    protected void configureEnvironment(final ClusterEnvironment.Builder builder) {
        try {
            URL systemResource = ClassLoader.getSystemResource("capella.pem");
            URL applicationyml = ClassLoader.getSystemResource("application.properties");
            log.error("CAPELLA.PEM -> " + systemResource);
            log.error("application.properties -> " + applicationyml);
            builder.securityConfig().enableTls(true).trustCertificate(Paths.get(systemResource.toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
