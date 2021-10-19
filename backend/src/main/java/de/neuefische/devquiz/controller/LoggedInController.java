package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.security.service.AppUserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class LoggedInController {

    private final AppUserDetailService appUserDetailService;

    public LoggedInController(AppUserDetailService appUserDetailService) {
        this.appUserDetailService = appUserDetailService;
    }

//    @GetMapping("me")
//    public UserDetails getLoggedInUser(Principal principal){
//
//        String username = principal.getName();
//
//        return appUserDetailService.loadUserByUsername(username);
//    }

    @GetMapping("me")
    public String getLoggedInUser(Principal principal){

         return principal.getName();
    }


}
