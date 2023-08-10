package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 내부에 componet 애노테이션이 있어서 자동으로 빈 등록, 아래 주석된거 풀고 이거 주석해도 똑같이 동작하긴함
//@Component
//@RequestMapping
public class SpringMemberFromControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form") // 해당 url이 호출 되면 실행하는 함수
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
