package com.demo.newproject.mapper;

import com.demo.newproject.model.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDAO {

    String TABLE_NAME = " user ";
    String INSERT_NAME = " account, password, head_url, salt, status";
    String SELECT_NAME = "id, " + INSERT_NAME;

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME})
    List<User> selectAllUser();

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id = #{id}"})
    User selectByuserId(@Param("id") int id);


    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where account = #{account}"})
    User selectByUserAccount(@Param("account") String account);

    @Insert({"insert into ", TABLE_NAME,
            " ( ", INSERT_NAME, " ) values (#{account}, #{password}, #{head_url}, #{salt}, #{status})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addUser(User user);

    @Select({"select count(id) from", TABLE_NAME, "where account = #{account}"})
    int isExist(String account);

    @Update({"update ", TABLE_NAME, " set head_url = #{head_url} where id = #{id}"})
    int updateHead_url(String head_url, int id);
}
