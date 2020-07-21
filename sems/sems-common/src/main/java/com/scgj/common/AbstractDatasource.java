package com.scgj.common;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableAutoConfiguration
@EnableConfigurationProperties
public abstract class AbstractDatasource {

	/** The Constant CONNECTION_WAIT_TIMEOUT_SECS. */
    private static final int CONNECTION_WAIT_TIMEOUT_SECS = 300;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDatasource.class);

    /** The Constant TEST_SQL. */
    private static final String TEST_SQL = "select 1 from dual";

   
    /** The database driver. */
    private String databaseDriver;

    /** The database password. */
    private String databasePassword;

    /** The database url. */
    private String databaseUrl;

    /** The database username. */
    private String databaseUsername;
        
    private String encryptionKey;

    	/** The initial size. */
    private String initialSize;

    /** The max pool size. */
    private String maxPoolSize;

    /** The min pool size. */
    private String minPoolSize;

    /** The pool name. */
    private String poolName;
  
    /**
     * Data source.
     *
     * @return the pool data source
     */
    protected DataSource configureDataSource() {

    	 PoolProperties pooledDataSource = new PoolProperties();

        try {
            pooledDataSource.setDriverClassName(databaseDriver);
            pooledDataSource.setUrl(databaseUrl);
            pooledDataSource.setUsername(databaseUsername);
            pooledDataSource.setInitialSize(Integer.parseInt(initialSize));
            pooledDataSource.setMaxActive(Integer.parseInt(maxPoolSize));
            pooledDataSource.setMinIdle(Integer.parseInt(minPoolSize));
            pooledDataSource.setValidationQuery(TEST_SQL);
            pooledDataSource.setSuspectTimeout(CONNECTION_WAIT_TIMEOUT_SECS);
            pooledDataSource.setName(poolName);
            pooledDataSource.setTestOnBorrow(true);
            pooledDataSource.setValidationInterval(300000);
				
            // derive password
            String password = getCustomPassword();
            if (StringUtils.isEmpty(password)) {
               throw new SQLException("Null password");
            }
            pooledDataSource.setPassword(password);
        } catch (Exception e) {
            LOGGER.error("Exception setting up datasource", e);
           
                pooledDataSource.setMinIdle(0);
                pooledDataSource.setMaxActive(0);
                pooledDataSource.setInitialSize(0);
           
        }

        LOGGER.debug("Setting up datasource for user:{} and databaseUrl:{}", databaseUsername, databaseUrl);
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(pooledDataSource);
        return datasource;
    }


    /**
     * Gets the custom password.
     *
     * @return the custom password
     */
    private String getCustomPassword() {

        String password = PasswordUtils.decrypt(encryptionKey,databasePassword);
        if (StringUtils.isEmpty(password)) {
            LOGGER.error("Unable to get password for shadow parms: " + databasePassword);
        }

        return password;
    }

    /**
     * Sets the database driver.
     *
     * @param databaseDriver the new database driver
     */
    public void setDatabaseDriver(String databaseDriver) {

        this.databaseDriver = databaseDriver;
    }

    /**
     * Sets the database password.
     *
     * @param databasePassword the new database password
     */
    public void setDatabasePassword(String databasePassword) {

        this.databasePassword = databasePassword;
    }

    /**
     * Sets the database url.
     *
     * @param databaseUrl the new database url
     */
    public void setDatabaseUrl(String databaseUrl) {

        this.databaseUrl = databaseUrl;
    }

    /**
     * Sets the database username.
     *
     * @param databaseUsername the new database username
     */
    public void setDatabaseUsername(String databaseUsername) {

        this.databaseUsername = databaseUsername;
    }

    /**
     * Sets the initial size.
     *
     * @param initialSize the new initial size
     */
    public void setInitialSize(String initialSize) {

        this.initialSize = initialSize;
    }

    /**
     * Sets the max pool size.
     *
     * @param maxPoolSize the new max pool size
     */
    public void setMaxPoolSize(String maxPoolSize) {

        this.maxPoolSize = maxPoolSize;
    }

    /**
     * Sets the min pool size.
     *
     * @param minPoolSize the new min pool size
     */
    public void setMinPoolSize(String minPoolSize) {

        this.minPoolSize = minPoolSize;
    }

    /**
     * Sets the pool name.
     *
     * @param poolName the new pool name
     */
    public void setPoolName(String poolName) {

        this.poolName = poolName;
    }


    	/**
	 * @return the encryptionKey
	 */
	public String getEncryptionKey() {
		return encryptionKey;
	}


	/**
	 * @param encryptionKey the encryptionKey to set
	 */
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}


	
	
}
