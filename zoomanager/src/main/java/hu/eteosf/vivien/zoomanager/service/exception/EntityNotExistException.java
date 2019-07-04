package hu.eteosf.vivien.zoomanager.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The given entity is not found.")
public class EntityNotExistException extends RuntimeException {

        public EntityNotExistException(String message) {
            super(message);
        }

        public EntityNotExistException(String message, Throwable cause) {
            super(message, cause);
        }

        public EntityNotExistException(Throwable cause) {
            super(cause);
        }

        public EntityNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

}
