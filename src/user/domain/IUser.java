package user.domain;
public interface IUser {
    User create(User user);
    void delete(String id);
    User update(String userId);
    User findByEmail(String email);
    User findById(String id);

}
