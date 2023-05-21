package smile.iceBulterrecipe.admin.exception;

public class NotExistMemoException extends RuntimeException{
    public NotExistMemoException(){super("수정된 신고 사유 메모가 없습니다. 다시 입력해주세요.");}

}
