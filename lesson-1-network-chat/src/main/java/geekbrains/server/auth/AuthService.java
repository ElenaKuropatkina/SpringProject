package geekbrains.server.auth;

import geekbrains.server.User;

public interface AuthService {

    boolean authUser(User user);
}
