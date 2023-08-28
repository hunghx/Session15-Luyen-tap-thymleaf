package ra.repository;

import ra.model.User;
import ra.model.UserDto;

public interface IUserRepository extends IRepository<User,Integer>{
    UserDto login(String username , String password);
}
