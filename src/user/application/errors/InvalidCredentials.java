package user.application.errors;

public class InvalidCredentials extends Error{
    public InvalidCredentials(String message){
        super(message);
    }
   public InvalidCredentials(){
       super("The provided email or password is incorrect");
   }
}
