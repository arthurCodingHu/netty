package com.concurrent.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-10 16:03
 * @Version: 1.0
 */
public class RecordQuery {

    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            int index = 1;
            for (Object o : params) {
                statement.setObject(index++, o);
            }
            ResultSet resultSet = statement.executeQuery();
            //调用RowHandler
            return handler.handle(resultSet);
        }
    }

}
