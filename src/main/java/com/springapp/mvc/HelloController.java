package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            InetAddress db = InetAddress.getByName("database.mydemo.com");
            String hostname = ip.getHostName();
            model.addAttribute("ip", ip.getHostAddress());
            model.addAttribute("hostname", hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "Java Demo Version 3");
		return "hello";
	}
}