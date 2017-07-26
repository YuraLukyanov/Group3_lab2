package model.implementetion.oracle;

import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


class Util {

    @SuppressWarnings("WeakerAccess")
     protected static void close(Connection connection, Exception globalException, Logger LOGGER) throws Exception {
        if (connection != null) {
            try {
                OracleDAOFactory.releaseConnection(connection);
            } catch(SQLException e){
                LOGGER.error("Can't to release a connection. Get an exception: " + e.toString());
                if (globalException != null) globalException.addSuppressed(e);
                else globalException = e;
                throw globalException;
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected static void rollback(Connection connection, Exception localException, Logger LOGGER) throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            LOGGER.error("Can't to make a rollback on the connection: " + connection.toString());
            if (localException != null) localException.addSuppressed(ex);
            else localException = ex;
            throw localException;
        }
    }

}
