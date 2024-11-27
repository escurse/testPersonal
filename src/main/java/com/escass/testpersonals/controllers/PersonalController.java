package com.escass.testpersonals.controllers;

import com.escass.testpersonals.entities.PersonalEntity;
import com.escass.testpersonals.results.Result;
import com.escass.testpersonals.services.PersonalService;
import com.escass.testpersonals.vos.PersonalVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/personal")
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "filter", required = false) String filter) {
        ModelAndView modelAndView = new ModelAndView();
        Pair<PersonalEntity[], PersonalVo> personals = this.personalService.getPersonals(page);
        if (search == null) {
            modelAndView.addObject("personals", personals.getLeft());
            modelAndView.addObject("vos", personals.getRight());
        } else {
            Pair<PersonalEntity[], PersonalVo> searchPersonals = this.personalService.getSearchPersonals(page, search, filter);
            modelAndView.addObject("personals", searchPersonals.getLeft());
            modelAndView.addObject("vos", searchPersonals.getRight());
            modelAndView.addObject("search", search);
            modelAndView.addObject("filter", filter);
        }
        modelAndView.setViewName("personal/index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postIndex(PersonalEntity personal) {
        JSONObject response = new JSONObject();
        Result result = this.personalService.add(personal);
        response.put(Result.NAME, result.nameToLower());
        return response.toString();
    }
}
