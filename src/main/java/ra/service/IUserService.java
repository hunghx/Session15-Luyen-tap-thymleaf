package ra.service;

import ra.model.FormRegisterDto;
import ra.model.User;
import ra.model.UserDto;

public interface IUserService extends IGennericService<User,Integer>{

    UserDto login(String username, String password);
    void doRegiter(FormRegisterDto formRegisterDto);

}
