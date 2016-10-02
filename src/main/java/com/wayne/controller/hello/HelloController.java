package com.wayne.controller.hello;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */

@RestController
public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(@RequestParam(required = false) String name) {
		return "hello, " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public List<String> hello2() {
		return ImmutableList.of("hello", "안녕", "1234");
	}
}
