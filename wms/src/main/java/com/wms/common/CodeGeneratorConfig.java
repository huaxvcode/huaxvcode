package com.wms.common;

public class CodeGeneratorConfig {

  public static String url;
  public static String username;
  public static String password;
  public static String author;
  public static String userDir;

  static {
    url = "jdbc:mysql://localhost:3306/wms?useSSL=false&serverTimezone=GMT%2B8";
    username = "root";
    password = "huaxvcode";
    author = "huaxvcode";
    userDir = System.getProperty("user.dir") + "/wms";
  }
}
