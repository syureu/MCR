package com.ssafy.mcr.exception;

public class NothingToPrefException extends RuntimeException{
    public NothingToPrefException() {
        super("선호하는 항목이 단 한개도 없습니다.");
    }
}
