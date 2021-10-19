package de.neuefische.devquiz.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactRoutingForward {

    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String forwardToRouteUrl(){
        return "forward:/";
    }
}
