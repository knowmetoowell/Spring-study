package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // 생성자 자동생성해서 의존관계 주입해줘여~
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody // html 없이 바로 문자를 바로 보내버리기
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        //myLogger = class hello.core.common.MyLogger$$SpringCGLIB$$0 가짜 프록시 클래스가 있음
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
