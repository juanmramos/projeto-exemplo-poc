package br.com.novaxs.crs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

}
