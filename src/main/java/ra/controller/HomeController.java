package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.model.FormRegisterDto;
import ra.model.User;
import ra.service.IUserService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IUserService userService;
@GetMapping("/")
    public String home(){
    return "home";
}
@GetMapping("/form-login")
    public String login(){
    return "login";
}
@GetMapping("/form-register")
    public String register(){
    return "register";
}
@GetMapping("/admin")
    public  String admin(){
    return "admin";
}
@PostMapping("/handle-register")
    public String doRegister(@RequestParam("username") String username, @RequestParam("full_name") String fullName , @RequestParam("password") String password, @RequestParam("avatar")MultipartFile avatar){
    userService.doRegiter(new FormRegisterDto(username,fullName,password,avatar));
    return "login";
}
}
