package com.Project01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping(value="", produces="text/html")
	public String index() {
		return "index";
	}

}
