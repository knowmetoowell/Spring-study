package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }


    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName, //request.getParameter("username") 랑 같은효과지용
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //request.getParameter("username") 랑 같은효과지용
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
//        int a = null;  -> 컴파일 오류 int에는 null을 넣을 수 없음
//        Integer b = null;

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    @ResponseBody // return값에 있는거 그냥 문자로 http 바디에 넣어버림 (RestController 랑 같은 효과!)
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); // helloData 안에 lombok.Data 에 toString이 있어서 걍 해줌ㅋ
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // 모델어트리 생략가능
        // String, Int, Integer 등 단순 타입은 @RequestParam 이 생략된걸로 인식하고 나머지는 @ModelAttribute 가 생략된걸로 인식함
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); // helloData 안에 lombok.Data 에 toString이 있어서 걍 해줌ㅋ
        return "ok";
    }
}
