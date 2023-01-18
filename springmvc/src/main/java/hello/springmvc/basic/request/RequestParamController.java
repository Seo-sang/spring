package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //리턴하는 문자열을 body에 넣어서 전송
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }


    @ResponseBody //리턴하는 문자열을 body에 넣어서 전송
    @RequestMapping("/request-param-v3")
    public String requestParamV3( //변수명과 같으면 @RequestParam의 name을 없앨 수 있다.
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }


    @ResponseBody //리턴하는 문자열을 body에 넣어서 전송
    @RequestMapping("/request-param-v4") //변수명과 같으면 @RequestParam도 없앨 수 있다.
    public String requestParamV4(
            String username,
            int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody //리턴하는 문자열을 body에 넣어서 전송
    @RequestMapping("/request-param-required") //required가 false면 안들어와도 된다. int는 null이 못들어가므로 Integer형으로 선언
    public String requestParamRequired(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody //리턴하는 문자열을 body에 넣어서 전송
    @RequestMapping("/request-param-map") //요청 파라미터를 map으로 받을 수 있음
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1") //모델의 데이터를 한번에 저장해줄 수 있음
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v2") //@ModelAttribute 생략 가능
    public String modelAttributeV2( HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
