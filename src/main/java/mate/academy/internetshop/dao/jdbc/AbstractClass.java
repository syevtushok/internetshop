package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;

public abstract class AbstractClass {
    protected final Connection connection;

    public AbstractClass(Connection connection) {
        this.connection = connection;
    }
}
