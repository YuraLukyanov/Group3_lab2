package model.implementetion.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Util {
    static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    static void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
