package com.zz.common.common.handler;

import com.zz.common.common.core.enums.BaseEnumsInterface;
import com.zz.common.common.utils.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName:MoneyTypeHandler
 * @Description:
 * @Author: vren
 * @Date: 2023/4/22 13:45
 */
public class EnumsTypeHandler<E extends BaseEnumsInterface> extends BaseTypeHandler<BaseEnumsInterface> {
    private Class<BaseEnumsInterface> type;

    public EnumsTypeHandler() {
    }

    public EnumsTypeHandler(Class<BaseEnumsInterface> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnumsInterface enums, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, enums.getValue());
    }

    @Override
    public BaseEnumsInterface getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return getEnums(resultSet.getString(s));
    }

    @Override
    public BaseEnumsInterface getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return getEnums(resultSet.getString(i));
    }

    @Override
    public BaseEnumsInterface getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return getEnums(callableStatement.getString(i));
    }

    private BaseEnumsInterface getEnums(String i) {
        return EnumUtils.getEnumByValue(type, i);
    }
}
