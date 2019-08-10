package com.concurrent.strategy;

import java.sql.ResultSet;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-10 16:02
 * @Version: 1.0
 */
public interface RowHandler<T> {
    T handle(ResultSet rs);
}
