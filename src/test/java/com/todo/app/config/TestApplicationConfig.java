package com.todo.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@TestConfiguration
public class TestApplicationConfig extends AbstractCouchbaseConfiguration {
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

}
