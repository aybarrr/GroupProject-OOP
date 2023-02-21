package Repositories.Interfaces;

import Entities.Admin;
import Entities.User;

public interface IAdminRepo {

    boolean SignIn(Admin admin);

    boolean createAdmin(Admin admin);
}
