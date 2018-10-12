package com.yejunyu.seckill.dao;

import com.yejunyu.seckill.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
