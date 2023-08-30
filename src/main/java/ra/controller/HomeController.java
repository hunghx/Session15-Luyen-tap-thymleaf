package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.FormRegisterDto;
import ra.model.User;
import ra.model.UserDto;
import ra.model.UserLoginDto;
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
    public ModelAndView login(){
    return new ModelAndView ("login","formDto",new UserLoginDto());
}
@GetMapping("/form-register")
    public ModelAndView register(){
    return new ModelAndView("register","formDto",new FormRegisterDto());
}
@GetMapping("/admin")
    public  String admin(){
    return "admin";
}
@PostMapping("/handle-register")
    public String doRegister(@ModelAttribute FormRegisterDto formDto){
    userService.doRegiter(formDto);
    return "login";
}
@PostMapping("/handle-login")
    public String handleLogin(HttpSession session, Model model,@ModelAttribute UserLoginDto formDto){
    // gửi dữ liệu từ form sáng service để xử lí
    UserDto userDto= userService.login(formDto.getUsername(),formDto.getPassword());
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
