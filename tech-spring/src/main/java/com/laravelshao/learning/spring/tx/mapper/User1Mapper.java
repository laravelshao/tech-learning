package com.laravelshao.learning.spring.tx.mapper;

import com.laravelshao.learning.spring.tx.pojo.User1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface User1Mapper {

    @Insert("insert into user1(name) values(#{name, jdbcType = VARCHAR})")
    int insert(User1 record);

    @Select("select * from user1 where id = #{id}")
    User1 selectByPrimaryKey(Integer id);
}