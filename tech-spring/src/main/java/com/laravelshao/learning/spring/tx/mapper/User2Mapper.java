package com.laravelshao.learning.spring.tx.mapper;

import com.laravelshao.learning.spring.tx.pojo.User2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface User2Mapper {

    @Insert("insert into user2(name) values(#{name, jdbcType = VARCHAR})")
    int insert(User2 record);

    @Select("select * from user2 where id = #{id}")
    User2 selectByPrimaryKey(Integer id);
}