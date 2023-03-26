package org.carrental.mapper;

import org.carrental.domain.User;
import org.carrental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements IMapper{

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASS = "pass";

    @Override
    public List resultSetToList(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()){
            User user = User.builder()
                    .id(rs.getInt(ID))
                    .username(rs.getString(USERNAME))
                    .password(rs.getString(PASS))
                    .build();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User resultSetToObject(ResultSet rs) throws SQLException {
        if ((rs.next())){
            return User.builder()
                    .id(rs.getInt(ID))
                    .username(rs.getString(USERNAME))
                    .password(rs.getString(PASS))
                    .build();

        }else {
            return null;
        }
    }
}
