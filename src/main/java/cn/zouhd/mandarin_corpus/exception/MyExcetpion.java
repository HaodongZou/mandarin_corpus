package cn.zouhd.mandarin_corpus.exception;

public class MyExcetpion extends RuntimeException {

    public MyExcetpion(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
