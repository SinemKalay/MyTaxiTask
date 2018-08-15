package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Driver is in OFFLINE state!")
public class OfflineDriverException extends Exception
{

    static final long serialVersionUID = -3387516993334229948L;


    public OfflineDriverException(String message)
    {
        super(message);
    }

}