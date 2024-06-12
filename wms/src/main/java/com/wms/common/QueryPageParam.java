package com.wms.common;

import java.util.Objects;
import java.util.TreeMap;
import lombok.Data;

@Data
public class QueryPageParam {

  private static int PAGE_SIZE = 20;
  private static int PAGE_NUM = 1;

  private int pageSize = PAGE_SIZE;
  private int pageNum = PAGE_NUM;
  private TreeMap<String, Object> params;
}
