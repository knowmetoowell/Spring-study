package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello") // get mapping에 적은 스트링으로 url 이동 가능
    public String hello(Model model){
        model.addAttribute("data", "hello!!");//모델에 에트리뷰트 추가
        // html에서 data 찾아서 hello!!를 매칭해줌
        //예시 : <p th:text="'안녕하세요. ' + ${data}" >안녕하세요. 손님</p>
        return "hello"; //리턴 스트링 값과 같은 이름을 가진 html 파일을 템플릿에서 찾음
    }
    @GetMapping("hello-mvc") //http://localhost:8080/hello-mvc?name=spring!!
    //@RequestParam(value = "name", required = false) 라고 쓰면 name이 꼭 없어도 됨
    // required 디폴트가 트루라서 ?name=~~ 이라고 꼭 적어줘야하고 안적어주면 오류남
    // (컨트롤 p 하면 매개변수 볼 수 있다)
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//viewResolver 사용하지않음 -> template에서 같은 이름의 html파일 찾지말고 바로 바디에 적용해버림
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // html 같은게 아니라 그냥 문자열 넘어감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 반환하고 리스폰스바디 있으면 제이슨으로 반환함 xml도 가능하긴함;
    }
    static class Hello{
        private String name;

        // alt + insert 하면 게터세터 나옴 ㅋ
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
