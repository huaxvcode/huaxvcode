package com.wms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author huaxvcode
 * @since 2024-06-08
 */
public interface UserMapper extends BaseMapper<User> {

  IPage<User> myPage(Page<User> page, String name);
}
