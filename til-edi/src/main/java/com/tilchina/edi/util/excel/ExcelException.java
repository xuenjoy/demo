package com.tilchina.edi.util.excel;

/**
 * Excel Exception
 * @since 2018-09-03
 *
 */
public class ExcelException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcelException() {
        super();
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
