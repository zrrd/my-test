package com.example.mytest.compent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author shaoyijiong
 * @date 2019/3/15
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Detail.class)
@Slf4j
public class JsonHandler extends BaseTypeHandler<Detail> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * json序列化
   */
  private Detail handler(String json) throws SQLException {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    try {
      return MAPPER.readValue(json, new TypeReference<Detail>() {
      });
    } catch (IOException e) {
      log.error("json序列化失败", e);
      throw new SQLException();
    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Detail o,
    JdbcType jdbcType) {

  }

  @Override
  public Detail getNullableResult(ResultSet rs, String s) throws SQLException {
    String jsonString = rs.getString(s);
    return handler(jsonString);
  }

  @Override
  public Detail getNullableResult(ResultSet rs, int i) throws SQLException {
    String jsonString = rs.getString(i);
    return handler(jsonString);
  }

  @Override
  public Detail getNullableResult(CallableStatement cs, int i) throws SQLException {
    String jsonString = cs.getString(i);
    return handler(jsonString);
  }
}
