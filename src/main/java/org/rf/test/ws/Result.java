package org.rf.test.ws;

public class Result<T> {
    private T value;
    private MessageError error;

    public Result(T value) {
        this.value = value;
    }

    public Result(MessageError error){
        this.error = error;
    }

    public boolean hasError() {
        return this.error != null;
    }

    public T getValue() {
        return value;
    }

    public MessageError getError() {
        return error;
    }
}
