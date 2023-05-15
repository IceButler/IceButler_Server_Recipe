package smile.iceBulterrecipe.food.exception;

public class ChatGPTErrorException extends RuntimeException {
    public ChatGPTErrorException(){
        super("chat gpt와 관련된 에러입니다.");
    }
}