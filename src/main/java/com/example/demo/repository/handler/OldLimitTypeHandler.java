package com.example.demo.repository.handler;

import com.example.demo.valueobject.OldLimit;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldLimitTypeHandler extends BaseTypeHandler<OldLimit> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, OldLimit oldLimit, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,oldLimit.getValue());
    }

    @Override
    public OldLimit getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return OldLimit.toEnum(resultSet.getInt(s));
    }

    @Override
    public OldLimit getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return OldLimit.valueOf(resultSet.getString(i));
    }

    @Override
    public OldLimit getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return OldLimit.toEnum(callableStatement.getInt(i));
    }
}
