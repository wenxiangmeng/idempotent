package idempotent.service;

import javax.servlet.http.HttpServletRequest;

public interface IdeMarkService {

    //创建幂等性
    String createIdeMark();

    //校验幂等性
    boolean checkIdeMark(HttpServletRequest request);
}
