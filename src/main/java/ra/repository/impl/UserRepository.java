package ra.repository.impl;

import org.springframework.stereotype.Repository;
import ra.model.User;
import ra.model.UserDto;
import ra.repository.IUserRepository;
import ra.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public List<User> findAll() {
        Connection conn = null;
        try{
            conn= ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("Select * from Accounts");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        Connection conn = null;
        User user = null;
        try{
            conn= ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("Select * from Accounts where id = ?");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullname"));
                user.setAvatarUrl(rs.getString("avatar_url"));
                user.setStatus(rs.getBoolean("status"));
                user.setRoleId(rs.getInt("role_id"));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection conn = null;
        try{
            CallableStatement callSt = null;
            conn= ConnectDB.getConnection();
            // kiem tra xem la xoa hay them moi
            if (findById(user.getId())==null){
                // them moi
                callSt = conn.prepareCall("INSERT  into Accounts(username,password,fullname,avatar_url) values (?,?,?,?)");
                callSt.setString(1,user.getUsername());
                callSt.setString(2,user.getPassword());
                callSt.setString(3,user.getFullName());
                callSt.setString(4,user.getAvatarUrl());
                callSt.executeUpdate();
            }else {
                // sua thong tin tai khoan

            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public UserDto login(String username, String password) {
        Connection conn = null;
        UserDto user = null;
        try{
            conn= ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("Select * from Accounts where username=? and password =?");
            callSt.setString(1,username);
            callSt.setString(2,password);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new UserDto();
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setAvatarUrl(rs.getString("avatar_url"));
                user.setStatus(rs.getBoolean("status"));
                user.setRoleId(rs.getInt("role_id"));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }
}
