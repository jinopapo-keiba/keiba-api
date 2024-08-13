package com.example.demo.repository.handler;

import com.example.demo.valueobject.BetType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BetTypeTypeHandler extends BaseTypeHandler<BetType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BetType betType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,betType.getValue());
    }

    @Override
    public BetType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return BetType.toEnum(resultSet.getInt(s));
    }

    @Override
    public BetType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return BetType.valueOf(resultSet.getString(i));
    }

    @Override
    public BetType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return BetType.toEnum(callableStatement.getInt(i));
    }
}
