package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //hello-template html
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute( "name", name);
        return "hello-template";

    }

    //그대로 데이터를 내려줌
    @GetMapping("hello-string")
    @ResponseBody //@ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
    public String helloString(@RequestParam("name") String name){
        return  "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        //단축기 alt + insert => getter, setter
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
