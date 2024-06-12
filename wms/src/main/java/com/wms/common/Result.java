package com.wms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 后端返回数据封装
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

  private int code;    // 编码 200/400
  private String msg;  // 成功/失败
    private Long total;  // 总记录数
  private Object data; // 数据

  public static Result result(int code, String msg, Long total, Object data) {
    return new Result(code, msg, total, data);
  }

  public static Result success() {
    return new Result(200, "成功", 0L, null);
  }

  public static Result success(Object data) {
    return new Result(200, "成功", 0L, data);
  }

  public static Result success(Object data, Long total) {
    return new Result(200, "成功", total, data);
  }

  public static Result fail() {
    return new Result(400, "失败", 0L, null);
  }
}
