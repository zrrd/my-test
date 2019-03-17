package com.example.mytest.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.mytest.compent.Detail;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shaoyijiong
 * @date 2019/3/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GameDetail extends Model<GameDetail> {

  private Integer id;
  private Detail detail;

  public GameDetail(Integer id) {
    this.id = id;
  }
}
