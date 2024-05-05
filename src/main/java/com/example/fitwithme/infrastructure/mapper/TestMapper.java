package com.example.fitwithme.infrastructure.mapper;

import com.example.fitwithme.domain.model.Test;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Repository
public interface TestMapper {

    // @Select(SELECT * FROM person WHERE id = #{id}") 으로 작성 가능. (보통 복잡한 쿼리만 xml 작성)

    Test find(Long id);

    int create(Test test);


}

