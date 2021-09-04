package com.example.phonevalidator.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.sqlite.Function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

@Configuration
public class SqlUtility {
    @Value("${spring.datasource.url}")
    public String databaseUrl;

    private Connection connection;

    private Statement statement;

    public Statement getStatement() {
        if (statement != null) {
            return statement;
        }
        try {
            connection = DriverManager.getConnection(databaseUrl);
            defineRegexFunction();
            statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void defineRegexFunction() throws SQLException {
        Function.create(connection, "REGEXP", new Function() {
            @Override
            protected void xFunc() throws SQLException {
                String expression = value_text(0);
                String value = value_text(1);
                if (value == null)
                    value = "";

                Pattern pattern = Pattern.compile(expression);
                result(pattern.matcher(value).find() ? 1 : 0);
            }
        });
    }


}
