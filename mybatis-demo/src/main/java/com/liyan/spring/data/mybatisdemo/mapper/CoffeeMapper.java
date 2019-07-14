package com.liyan.spring.data.mybatisdemo.mapper;

import com.liyan.spring.data.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author Li Yan
 * 2019/7/14 17:46
 */
@Mapper
@Component
public interface CoffeeMapper {

    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + "values(#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    Long save(Coffee coffee);


    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            //map-underscore-to-camel-case = true可以实现一样的效果
            @Result(column = "update_time", property = "updateTime")
    })
    Coffee findById(@Param("id") Long id);
}
