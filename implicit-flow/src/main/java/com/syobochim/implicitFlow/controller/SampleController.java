package com.syobochim.implicitFlow.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author syobochim
 */
@Controller
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
