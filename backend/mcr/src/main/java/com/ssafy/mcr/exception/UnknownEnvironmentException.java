package com.ssafy.mcr.exception;

public class UnknownEnvironmentException extends RuntimeException{
    public UnknownEnvironmentException() {
        super("실행환경을 결정 할 수 없습니다.");
    }
}
