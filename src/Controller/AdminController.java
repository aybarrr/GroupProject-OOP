package Controller;

import Entities.Admin;
import Repositories.Interfaces.IAdminRepo;
import Repositories.Interfaces.IUserRepo;

public class AdminController {
    private final IAdminRepo repo;

    public AdminController(IAdminRepo repo) {
        this.repo = repo;
    }

    public String SignIn(String name, String password){

        boolean signin = repo.SignIn(name, password);
        return (signin ? "Admin was Signed in!" : "Admin Sign in process was failed!");
    }
}
