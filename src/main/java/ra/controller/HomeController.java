package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.model.FormRegisterDto;
import ra.model.User;
import ra.model.UserDto;
import ra.service.IUserService;

import javax.servlet.http.HttpSession;

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
@PostMapping("/handle-login")
    public String handleLogin(HttpSession session, Model model, @RequestParam("username") String username, @RequestParam("password") String password){
    // gửi dữ liệu từ form sáng service để xử lí
    UserDto userDto= userService.login(username,password);
    if(userDto==null){
//        tài khaonr không tồn tại
        model.addAttribute("error","Tài khoản không chinhs xác!");
        return "login";
    }else {
        if(userDto.getRoleId()==1){
            // quyền admin, điều hướng ddeens admin
            session.setAttribute("adminlogin",userDto);
            return "admin";
        }else {
            // quyền user, điều hướng đến user
//            kiểm tra tài khoản có bị khóa không
            if(userDto.isStatus()){
                // lưu lên session
                session.setAttribute("userlogin",userDto);
                return "home";
            }else {
                // tài khoản bị khóa
                model.addAttribute("error","Tài khoản đã bị khóa!");
                return "login";
            }
        }
    }
}
@GetMapping("/logout")
    public String logout(HttpSession session){
    session.removeAttribute("userlogin");
    return "redirect:/"; // redirect là điều hướng theo đường dẫn
}
}
