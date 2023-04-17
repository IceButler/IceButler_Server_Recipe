package smile.iceBulterrecipe.user.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(){
    super("요청한 idx를 가진 유저를 찾을 수 없습니다.");
  }

}
