package smile.iceBulterrecipe.global.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCustom<T>{

    private T data;
    private LocalDateTime transaction_time;
    private HttpStatus status;
    private String description;
    private int statusCode;

    // OK
    public static <T> ResponseCustom<T> CREATED(@Nullable T data) {
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> ResponseCustom<T> OK(@Nullable T data) {
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> ResponseCustom<T> OK() {
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public static <T> ResponseCustom<T> x(@Nullable String description){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .description(description)
                .build();
    }

    public static <T> ResponseCustom<T> BAD_REQUEST(@Nullable T data){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(data)
                .build();
    }

    public static <T> ResponseCustom<T> NOT_FOUND(@Nullable T data){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .data(data)
                .build();
    }

    public static <T> ResponseCustom<T> NOT_FOUND(@Nullable String description){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .description(description)
                .build();
    }

    public static <T> ResponseCustom<T> FORBIDDEN(){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN)
                .statusCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

    public static <T> ResponseCustom<T> FORBIDDEN(String description){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN)
                .statusCode(HttpStatus.FORBIDDEN.value())
                .description(description)
                .build();
    }



    public static <T> ResponseCustom<T> UNAUTHORIZED(){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }

    public static <T> ResponseCustom<T> INTERNAL_SERVER_ERROR(){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    public static <T> ResponseCustom<T> INTERNAL_SERVER_ERROR(String description){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .description(description)
                .build();
    }

    public static <T> ResponseCustom<T> JWT_EXPIRED(){
        return (ResponseCustom<T>) ResponseCustom.builder()
                .transaction_time(LocalDateTime.now())
                .description("JWT_EXPIRED")
                .statusCode(441)
                .build();
    }
}
