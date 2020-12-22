package com.gudyna.webproject.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final int POOL_SIZE = 8;
    private BlockingQueue<ProxyConnection> involvedConnections;
    private BlockingQueue<ProxyConnection> freeConnections;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    public static final String DB_PROPERTIES = "db";

    private ConnectionPool() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES);
        String url = resourceBundle.getString("db.url");
        String user = resourceBundle.getString("db.user");
        String password = resourceBundle.getString("db.password");
        this.freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        this.involvedConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("impossible create connection to db or with creation driver");
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            involvedConnections.offer(connection);
            connection.setAutoCommit(true);
        } catch (InterruptedException | SQLException e) {
            LOGGER.log(Level.ERROR, "Impossible provide connection or set auto commit");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass().equals(ProxyConnection.class)) {
            if (involvedConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            } else {
                LOGGER.log(Level.ERROR, "Pool does not include given connection");
            }
        } else {
            LOGGER.log(Level.ERROR, "Given connection is not a proxy connection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                LOGGER.log(Level.ERROR, "Impossible close connection", e);
            }
        }
        deregisterDrivers();
    }

    public void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Impossible deregister driver", e);
            }
        }
    }
}
