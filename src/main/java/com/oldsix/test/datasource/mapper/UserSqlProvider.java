package com.oldsix.test.datasource.mapper;

import com.oldsix.test.datasource.dto.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users");
        if(record.getBirthday() != null){
            sql.VALUES("user_birthday", "#{birthday,jdbcType=TIMESTAMP}");
        }
        if(record.getCreateTime() != null){
            sql.VALUES("user_createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }
        if(record.getName() != null){
            sql.VALUES("user_name", "#{name,jdbcType=VARCHAR}");
        }
        if(record.getSex() != null){
            sql.VALUES("user_sex", "#{sex,jdbcType=VARCHAR}");
        }
        if(record.getStatus() != null){
            sql.VALUES("user_status", "#{status,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    public String selectByExample() {
        SQL sql = new SQL();
        sql.SELECT("id");
        sql.SELECT("user_birthday");
        sql.SELECT("user_createTime");
        sql.SELECT("user_name");
        sql.SELECT("user_sex");
        sql.SELECT("user_status");
        sql.FROM("users");
        return sql.toString();
    }

}
