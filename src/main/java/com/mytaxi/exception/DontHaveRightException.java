package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Do not have right to do")
public class DontHaveRightException extends Exception
{

    static final long serialVersionUID = -3387516993334229948L;


    public DontHaveRightException(String message)
    {
        super(message);
    }

}