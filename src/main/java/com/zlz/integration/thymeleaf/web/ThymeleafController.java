package com.zlz.integration.thymeleaf.web;

import com.zlz.integration.thymeleaf.domain.Proxy;
import com.zlz.integration.thymeleaf.service.ProxyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThymeleafController {

    private static final String BOOK_FORM_PATH_NAME = "proxyForm";
    private static final String BOOK_LIST_PATH_NAME = "proxyList";
    private static final String REDIRECT_TO_BOOK_URL = "redirect:/proxy";

    @Autowired
    ProxyStoreService proxyStoreService;


    @RequestMapping(method = RequestMethod.GET,value = "/proxy")
    public String getBookList(ModelMap map) {
        map.addAttribute("proxyList", proxyStoreService.findAll());
        return BOOK_LIST_PATH_NAME;
    }


    @RequestMapping(value = "/proxy/create", method = RequestMethod.GET)
    public String createBookForm(ModelMap map) {
        map.addAttribute("proxy", new Proxy());
        map.addAttribute("action", "create");
        return BOOK_FORM_PATH_NAME;
    }


    @RequestMapping(value = "/proxy/create", method = RequestMethod.POST)
    public String postBook(@ModelAttribute Proxy proxy) {
        proxyStoreService.insertByBook(proxy);
        return REDIRECT_TO_BOOK_URL;
    }

    @RequestMapping(value = "/proxy/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("proxy", proxyStoreService.findById(id));
        map.addAttribute("action", "update");
        return BOOK_FORM_PATH_NAME;
    }


    @RequestMapping(value = "/proxy/update", method = RequestMethod.POST)
    public String putBook(@ModelAttribute Proxy proxy) {
        proxyStoreService.update(proxy);
        return REDIRECT_TO_BOOK_URL;
    }


    @RequestMapping(value = "/proxy/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id) {
        proxyStoreService.delete(id);
        return REDIRECT_TO_BOOK_URL;
    }

}
