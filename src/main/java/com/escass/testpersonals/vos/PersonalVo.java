package com.escass.testpersonals.vos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalVo {
    public final int countPerPage = 5; // 보여줄 갯수
    public final int page; // 넘겨받을 파라미터 수
    public final int totalCount; // 전체 갯수
    public final int movableMinPage = 1; // 최소로 움직일 수 있는 페이지
    public final int movableMaxPage; // 최대로 움직일 수 있는 페이지
    public final int offsetCount; // 제외할 갯수

    public PersonalVo(int page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;
        this.movableMaxPage = totalCount % countPerPage == 0 ? totalCount / countPerPage : totalCount / countPerPage + 1;
        this.offsetCount = (page - 1) * countPerPage;
    }
}
