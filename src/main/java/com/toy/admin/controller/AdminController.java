package com.toy.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ihanbin on 2017. 6. 17..
 */
@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * 관리자 페이지 메인화면 이동 메소드
     * @return
     */
    @Secured({"ROLE_ADMIN" , "ROLE_MASTER"})
    @RequestMapping(value="/admin")
    public String adminPage() {

        return "admin/admin";
    }
}
