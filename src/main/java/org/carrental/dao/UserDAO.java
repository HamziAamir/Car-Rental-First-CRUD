package org.carrental.dao;

import org.carrental.domain.User;
import org.carrental.mapper.IMapper;
import org.carrental.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.*;

public class UserDAO extends BaseDAO implements ICrud<User>{
    private final IMapper<User> userMapper = new UserMapper();
    public User getUserByUsernameAndPassword(String username , String password){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_USER_BY_USERNAME_PASSWORD);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_USER);
            ps.setString(1,obj.getUsername());
            ps.setString(2,obj.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_USER);
            return userMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER_BY_ID);
            ps.setString(1,obj.getUsername());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_USER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where username like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
