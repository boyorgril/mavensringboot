package com.oldsix.test.datasource.mapper;

import com.oldsix.test.datasource.dto.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface UserMapper {

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(User record);

    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_birthday", property="birthday", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="user_createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="user_name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_sex", property="sex", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectByExample();

    @Update({
            "update users",
            "set user_birthday = #{birthday,jdbcType=TIMESTAMP},",
            "user_createTime = #{createTime,jdbcType=TIMESTAMP},",
            "user_sex = #{sex,jdbcType=VARCHAR},",
            "user_name = #{name,jdbcType=VARCHAR},",
            "user_status = #{status,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

}
