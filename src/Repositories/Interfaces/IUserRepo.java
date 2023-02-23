package Repositories.Interfaces;

import Entities.User;

import java.util.List;

public interface IUserRepo{
    boolean createUser(User user);
    boolean SignIn(User user);
    User getUser(int id);
    List<User> getAllUsers();
    void setLvl(User user, int lvl);
}
