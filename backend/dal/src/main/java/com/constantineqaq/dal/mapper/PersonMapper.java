package com.constantineqaq.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.constantineqaq.base.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
