package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok이 제공
@RestController//return 값에 있는 문자열을 그대로 http 바디에 반영해줌, @Controller는 뷰 리졸버 찾아서 뷰 반환해줌(뷰 이름으로 인식)
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);


        log.trace("trace log = " + name );
        log.trace("trace log = {}", name);
        // 위의 결과는 동일하다, 그러나 더하기로 하면 연산이 일어나서 쓸데없이 cpu 낭비가 일어난다.
        // 어차피 debug레벨로 설정해놔서 trace는 출력하지도 않는데 더하기 연산이 일어나서 낭비임

        log.debug("debug log = {}", name);
        log.info(" info log={}", name);
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
