package Controller;

import Entities.User;
import Repositories.IUserRepo;

import java.util.List;

public class UserController {
    private final IUserRepo repo;

    public UserController(IUserRepo repo) {
        this.repo = repo;
    }

    public String createUser(String name, String nickname) {
        User user = new User(name, nickname);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");

    }

    public String SingIn(String name, String nickname) {
        User user = new User(name, nickname);

        boolean signin = repo.SignIn(user);
        return (signin ? "User was Signed in!" : "User Sign in process was failed!");
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        return users.toString();
    }

    public void setLevel(User user, int lvl){
        repo.setLvl(user, lvl);
    }

}
