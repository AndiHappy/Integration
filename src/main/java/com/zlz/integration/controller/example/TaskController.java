package com.zlz.integration.controller.example;

import com.zlz.integration.db.mongodb.dao.IntegraTaskRepository;
import com.zlz.integration.loadresource.ResourceService;
import com.zlz.integration.task.IntegraTask;
import com.zlz.integration.thymeleaf.domain.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类似于定制化任务：
 * step1: 确定入参，并且检查校验入参
 * step2: 根据步骤的设置，输入使用的入参，产生step2的出参
 * step3：根据入参，step2的出参，step3的步骤设置，输入使用的入参，产生step3的出参
 * step4: ......
 * step5: ......
 * 处理不同的异常的情况，或者
 * */

@Controller
public class TaskController {

    @Autowired
    IntegraTaskRepository integraTaskRepository;

    @GetMapping("/task")
    public String resourceList(Model model) {
        model.addAttribute("integraTaskList", integraTaskRepository.findAll());
        return "taskList";
    }

    @RequestMapping(value = "/task/create", method = RequestMethod.GET)
    public String createBookForm(ModelMap map) {
        map.addAttribute("task", new IntegraTask());
        map.addAttribute("action", "create");
        return "taskForm";
    }

}
