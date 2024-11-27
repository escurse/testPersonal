package com.escass.testpersonals.services;

import com.escass.testpersonals.entities.PersonalEntity;
import com.escass.testpersonals.mappers.PersonalMapper;
import com.escass.testpersonals.results.personal.AddResult;
import com.escass.testpersonals.vos.PersonalVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalMapper personalMapper;

    public AddResult add(PersonalEntity personal) {
        if (personal == null ||
                personal.getName() == null || personal.getName().length() < 2 || personal.getName().length() > 10 ||
                personal.getGender() == null ||
                personal.getSsnBirth() == null || personal.getSsnBirth().length() != 6 ||
                personal.getSsnKey() == null || personal.getSsnKey().length() != 7 ||
                personal.getRegion() == null) {
            return AddResult.FAILURE;
        }
        try {
            Integer.parseInt(personal.getSsnBirth());
            Integer.parseInt(personal.getSsnKey());
        } catch (Exception e) {
            return AddResult.FAILURE;
        }
        return this.personalMapper.insertPersonal(personal) > 0
                ? AddResult.SUCCESS
                : AddResult.FAILURE;
    }

    public Pair<PersonalEntity[], PersonalVo> getPersonals(int page) {
        int totalCount = this.personalMapper.selectPersonalCount();
        PersonalVo personalVo = new PersonalVo(page, totalCount);
        return Pair.of(this.personalMapper.selectPersonals(page, personalVo.countPerPage, personalVo.offsetCount), personalVo);
    }

    public Pair<PersonalEntity[], PersonalVo> getSearchPersonals(int page, String search, String filter) {
        if (filter.equals("gender") && search.equals("남")) {
            search = "M";
        }
        if (filter.equals("gender") && search.equals("여")) {
            search = "F";
        }
        int totalCount = this.personalMapper.selectSearchPersonalCount(search, filter);
        PersonalVo personalVo = new PersonalVo(page, totalCount);
        return Pair.of(this.personalMapper.selectSearchPersonals(page, search, filter, personalVo.countPerPage, personalVo.offsetCount), personalVo);
    }
}
