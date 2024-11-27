package com.escass.testpersonals.mappers;

import com.escass.testpersonals.entities.PersonalEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonalMapper {
    int insertPersonal(PersonalEntity personal);

    int selectPersonalCount();

    int selectSearchPersonalCount(@Param(value = "search") String search,
                                  @Param(value = "filter") String filter);

    PersonalEntity[] selectPersonals(@Param(value = "page") int page,
                                     @Param(value = "limitCount") int limitCount,
                                     @Param(value = "offsetCount") int offsetCount);

    PersonalEntity[] selectSearchPersonals(@Param(value = "page") int page,
                                           @Param(value = "search") String search,
                                           @Param(value = "filter") String filter,
                                           @Param(value = "limitCount") int limitCount,
                                           @Param(value = "offsetCount") int offsetCount);
}
