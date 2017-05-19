package com.xpizza.vclemgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 */
@Controller
@RequestMapping("console")
public class ConsoleController {

	static final Logger logger = LoggerFactory.getLogger(ConsoleController.class);

	@RequestMapping("home.page")
	public void showPages() {
	}

}
