package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.model.FormRegisterDto;
import ra.model.User;
import ra.model.UserDto;
import ra.repository.IUserRepository;
import ra.service.IUserService;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service

public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    private String pathImage = "C:\\Users\\hung1\\DemoLogin\\DemoLogin\\src\\main\\webapp\\WEB-INF\\upload\\";


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public UserDto login(String username, String password) {
        return null;
    }

    @Override
    public void doRegiter(FormRegisterDto formRegisterDto) {
        // trieenr khai nghiep vu
        // upload file
        String filename = formRegisterDto.getAvatar().getOriginalFilename();
        try {
            FileCopyUtils.copy(formRegisterDto.getAvatar().getBytes(),new File(pathImage+filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(new User(formRegisterDto.getUsername(), formRegisterDto.getPassword(), formRegisterDto.getFullName(), filename));
    }
}
