package Controller;

import Entities.User;
import Repositories.Interfaces.IUserRepo;

public class UserController {
    private final IUserRepo repo;

    public UserController(IUserRepo repo) {
        this.repo = repo;
    }

    public String createUser(String name, String nickname, String password) {
        User user = new User(name, nickname, password);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");

    }

    public String SingIn(String name, String nickname, String password) {
        User user = new User(name, nickname, password);

        boolean signin = repo.SignIn(user);
        return (signin ? "User was Signed in!" : "User Sign in process was failed!");
    }

}
