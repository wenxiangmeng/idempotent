package idempotent.exception;

/**
 * 错误枚举编码
 */
public enum ApiCode {
    /**
     * 自定义异常Repeat operation
     */
    REPEAT_OPERATION(101,"重复性操作"),
    ;


    private Integer errno;

    private String errmsg;

    ApiCode(Integer errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
