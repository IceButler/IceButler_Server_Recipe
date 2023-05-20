package smile.iceBulterrecipe.admin.exception;

public class RecipeReportNotFoundException extends RuntimeException{
    public RecipeReportNotFoundException(){super("요청한 idx를 가진 레시피 신고를 찾을 수 없습니다");}
}
