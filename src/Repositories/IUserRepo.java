package Repositories;

import Entities.User;

import java.util.List;

public interface IUserRepo {
    boolean createUser(User user);
//    boolean CreateUserTable();
    User getUser(int id);
    List<User> getAllUsers();

    void setLvl(User user, int lvl);
}
