package idempotent.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -9138712164205129842L;

    private ApiCode apiCode;

    public ApiException(ApiCode apiCode) {
        this.apiCode = apiCode;
    }

    public ApiException(String message, ApiCode apiCode) {
        super(message);
        this.apiCode = apiCode;
    }

    public ApiException(String message, Throwable cause, ApiCode apiCode) {
        super(message, cause);
        this.apiCode = apiCode;
    }

    public ApiException(Throwable cause, ApiCode apiCode) {
        super(cause);
        this.apiCode = apiCode;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ApiCode apiCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.apiCode = apiCode;
    }

    public ApiCode getApiCode() {
        return apiCode;
    }

    public void setApiCode(ApiCode apiCode) {
        this.apiCode = apiCode;
    }

    public Map<String, Object> getResultMap() {
        Map<String, Object> result = new HashMap<>(2);
        result.put("errno", apiCode.getErrno());
        result.put("errmsg", apiCode.getErrmsg());
        return result;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "ApiCode=" + apiCode.toString() +
                '}';
    }
}
