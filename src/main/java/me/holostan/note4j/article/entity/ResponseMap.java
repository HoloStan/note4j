package me.holostan.note4j.article.entity;

/**
 * {
 *     "status":,
 *     "error":
 *     "exception":
 *     "message":
 *     "data":
 * }
 * Created by ghu on 7/26/2017.
 */
public class ResponseMap {

    private Integer status;
    private String error;
    private String exception;
    private String message;
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMap{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
