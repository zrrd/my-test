package com.example.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author shaoyijiong
 * @date 2020/9/9
 */
public interface T1Mapper {

  @Select("select count(*) from hello")
  Integer count();
}
