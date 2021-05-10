package dev.protobot.blogcustom.exceptions;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException{

    public SpringRedditException(String exceptionMessage, MailException e){
        super(exceptionMessage);
    }

    public SpringRedditException(String invalid_token) {
    }
}
