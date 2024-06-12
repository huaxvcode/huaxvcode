package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.IUserService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huaxvcode
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @GetMapping("/list")
  public Result list() {
    return Result.success(userService.list());
  }

  // 新增
  @PostMapping("/save")
  public Result save(@RequestBody User user) {
    return Result.success(userService.save(user));
  }

  // 修改
  @PostMapping("/mod")
  public Result mod(@RequestBody User user) {
    return Result.success(userService.updateById(user));
  }

  // 新增或修改
  @PostMapping("/saveOrMod")
  public Result saveOrMod(@RequestBody User user) {
    return Result.success(userService.saveOrUpdate(user));
  }

  // 删除
  @GetMapping("/delete")
  public Result delete(Integer id) {
    return Result.success(userService.removeById(id));
  }

  // 查询（模糊、匹配）
  @PostMapping("/listP")
  public Result listP(@RequestBody User user) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    if (StringUtils.isNotBlank(user.getName())) {
      queryWrapper.like(User::getName, user.getName());
    }
//    queryWrapper.eq(User::getName, user.getName());
    var res = userService.list(queryWrapper);
    return Result.success(res, (long)res.size());
  }

  // 分页查询
  @PostMapping("/listPage")
  public Result listPage(@RequestBody QueryPageParam queryPageParam) {
    var params = queryPageParam.getParams();
    IPage<User> res;

    Page<User> page = new Page<>();
    page.setCurrent(queryPageParam.getPageNum());
    page.setSize(queryPageParam.getPageSize());

    if (params != null && params.containsKey("name")) {
      var name = (String) params.get("name");
      LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.like(User::getName, name);

//    res = userService.page(page, queryWrapper);
      res = userService.myPage(page, name);
    }
    else {
      res = userService.page(page);
    }

    return Result.success(res.getRecords(), res.getTotal());
  }
}
