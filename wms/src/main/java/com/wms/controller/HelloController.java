package com.wms.controller;

import com.wms.entity.User;
import com.wms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  @Autowired
  private IUserService iUserService;

  @GetMapping("/list")
  public List<User> list() {
    return iUserService.list();
  }
}
