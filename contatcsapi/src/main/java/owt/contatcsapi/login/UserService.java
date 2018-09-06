package owt.contatcsapi.login;

import owt.contatcsapi.model.User;

public interface UserService {
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
