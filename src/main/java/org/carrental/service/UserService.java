package org.carrental.service;

import org.carrental.dao.UserDAO;
import org.carrental.domain.Customer;
import org.carrental.domain.User;

import java.util.List;

public class UserService {

    UserDAO dao =new UserDAO();

    public String[][] searchByName(String name){
        List<User> userList = dao.getByName(name);
        return transformToJTable(userList,5);
    }

    public String[][] getAllUserForJTable(){
        List<User> userList = dao.getAll();
        return transformToJTable(userList,6);
    }

    private String[][] transformToJTable(List<User> userList,int columnSize){
        String[][] data = new String[userList.size()][columnSize];

        for (int i = 0; i < userList.size() ; i++) {
            data[i][0] = String.valueOf(userList.get(i).getId());
            data[i][1] = userList.get(i).getUsername();
            data[i][2] = userList.get(i).getPassword();
        }
        return data;
    }

    public void save(String username, String password) {

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        dao.insert(user);
    }


    public void delete(String id){
            dao.deleteByID(Long.parseLong(id));
        }
    public void update(String name, Long id) {
        dao.updateUI(name,id);
    }
}
