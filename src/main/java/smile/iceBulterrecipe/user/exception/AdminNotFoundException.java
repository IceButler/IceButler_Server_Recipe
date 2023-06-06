package smile.iceBulterrecipe.user.exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(){ super("요청한 idx를 가진 관리자를 찾을 수 없습니다.");}
}
