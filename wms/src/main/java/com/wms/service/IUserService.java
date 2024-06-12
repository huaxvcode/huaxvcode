package com.wms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huaxvcode
 * @since 2024-06-08
 */
public interface IUserService extends IService<User> {
  IPage<User> myPage(Page<User> page, String name);
}
