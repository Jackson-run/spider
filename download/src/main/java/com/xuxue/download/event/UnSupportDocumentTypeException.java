package com.xuxue.download.event;

public class UnSupportDocumentTypeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnSupportDocumentTypeException() {
        super();
    }

    public UnSupportDocumentTypeException(String s) {
        super(s);
    }

    public UnSupportDocumentTypeException(Exception e) {
        super(e);
    }

}
