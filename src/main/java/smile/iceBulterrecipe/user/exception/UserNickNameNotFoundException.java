package smile.iceBulterrecipe.user.exception;

public class UserNickNameNotFoundException extends RuntimeException{
    public UserNickNameNotFoundException(){super("요청한 닉네임을 가진 유저를 찾을 수 없습니다.");}
}
