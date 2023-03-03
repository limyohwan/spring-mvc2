package hello.thymeleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;


@Controller
@RequestMapping("/basic")
public class BasicController {
    
    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "hello world");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textunescaped(Model model){
        model.addAttribute("data", "hello <b>world</b>");
        return "basic/text-unescaped";
    }

    @GetMapping(value="/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "HelloSession");
        return "basic/basic-objects";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/date")
    public String date(Model model){
        return "basic/date";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "data1");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "data1");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "data1");
        return "basic/attribute";
    }

    @GetMapping(value="/each")
    public String each(Model model) {
        List<User> list = addUsers();

        model.addAttribute("users", list);

        return "basic/each";
    }

    @GetMapping(value="/condition")
    public String condition(Model model) {
        List<User> list = addUsers();

        model.addAttribute("users", list);

        return "basic/condition";
    }

    @GetMapping(value="/comments")
    public String comments(Model model) {
        List<User> list = addUsers();

        model.addAttribute("data", "Spring!");

        return "basic/comments";
    }

    @GetMapping(value="/block")
    public String block(Model model) {
        List<User> list = addUsers();

        model.addAttribute("users", list);

        return "basic/block";
    }

    @GetMapping(value="/javascript")
    public String javascript(Model model) {
        List<User> list = addUsers();

        model.addAttribute("users", list);
        model.addAttribute("user", list.get(0));


        return "basic/javascript";
    }

    private List<User> addUsers() {
        List<User> list = new ArrayList<>();

        User userA = new User("userA", 10);
        User userB = new User("userB", 20);
        User userC = new User("userC", 30);
        list.add(userA);
        list.add(userB);
        list.add(userC);
        return list;
    }


    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }
    
    @Data
    static class User{
        private String username;
        private int age;
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
        
    }
}
