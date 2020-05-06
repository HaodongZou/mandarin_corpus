package cn.zouhd.mandarinCorpus.exception;

public class MyExcetpion extends RuntimeException {

    public MyExcetpion(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
