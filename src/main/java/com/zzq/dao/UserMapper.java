package com.zzq.dao;

import com.zzq.annotation.SubDatabaseTable;
import com.zzq.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    int deleteByPrimaryKey(Long id);


    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    int insert(User record);


    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    int insertSelective(User record);


    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    User selectByPrimaryKey(Long id);
    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")

    int updateByPrimaryKeySelective(User record);
    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    int updateByPrimaryKey(User record);

    @SubDatabaseTable(dataBaseName = "multi",tableName = "user")
    @Update("update {0} set user_name=#{userName},pass_word=#{passWord} where id=#{id}")
    int updateName(@Param("id") Long id,@Param("userName") String userName,@Param("passWord") String passWord);
}