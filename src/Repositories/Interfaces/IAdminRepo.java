package Repositories.Interfaces;

import Entities.Admin;
import Entities.User;

public interface IAdminRepo {

    boolean SignIn(String name, String password);


}
