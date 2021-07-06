package com.zlz.integration.controller.example;

import com.zlz.integration.loadresource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ResourceController {

	@Autowired
	ResourceService resourceService;

	@GetMapping("/resource")
	public String resourceList(Model model) {
		model.addAttribute("fileResource", resourceService.findAll());
		return "resourceList";
	}
}