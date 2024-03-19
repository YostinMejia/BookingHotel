package user.application;

import org.mindrot.jbcrypt.BCrypt;
import user.domain.IUser;
import user.domain.User;

public class UserService {
    private IUser UserRepository;
    public UserService(IUser UserRepository){
        this.UserRepository = UserRepository;
    }

    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    private boolean verifyPassword(String hashedPassword, String plainPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    public User register(User User){
        String idFound = this.UserRepository.findByEmail(User.getEmail()).getId();
        if (idFound !=null){
            throw new RuntimeException("Email registered.");
        }
        String hashedPwd= this.hashPassword(User.getPassword());
        return new User(User.getId(), User.getName(),User.getEmail(), hashedPwd );
    }

}
