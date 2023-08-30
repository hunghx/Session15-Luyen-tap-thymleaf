package ra.repository.impl;

import org.springframework.stereotype.Repository;
import ra.model.Catalog;
import ra.repository.ICatalog;
import ra.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CatalogRepository implements ICatalog {
    @Override
    public List<Catalog> findAll() {
        List<Catalog> list = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("SELECT  * From Catalog");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
               Catalog cat = new Catalog();
               cat.setId(rs.getLong("id"));
               cat.setName(rs.getString("name"));
               cat.setDescription(rs.getString("description"));
               cat.setStatus(rs.getBoolean("status"));
                list.add(cat);
            }
        }catch (SQLException e){
            throw   new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Catalog findById(Long id) {
        Connection conn =null;
        Catalog cat = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("SELECT  * From Catalog WHERE id=?");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                cat.setStatus(rs.getBoolean("status"));
            }
        }catch (SQLException e){
            throw   new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return cat;
    }

    @Override
    public void save(Catalog catalog) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.getConnection();
            if(catalog.getId()==null){
//            thêm mới
                callSt = conn.prepareCall("insert into catalog(name,description) values (?,?)");
                callSt.setString(1,catalog.getName());
                callSt.setString(2,catalog.getDescription());
                callSt.executeUpdate();
            }else {
//            cập nhật
                callSt = conn.prepareCall("UPDATE catalog set name=?, description=?,status=? where id =?");
                callSt.setString(1,catalog.getName());
                callSt.setString(2,catalog.getDescription());
                callSt.setBoolean(3,catalog.isStatus());
                callSt.setLong(4,catalog.getId());
                callSt.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("DELETE  from catalog where id = ?");
            callSt.setLong(1,id);
            callSt.executeUpdate();

        }catch (SQLException e){
            throw   new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
