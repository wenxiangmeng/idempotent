package idempotent.service.impl;

import idempotent.exception.ApiCode;
import idempotent.exception.ApiException;
import idempotent.service.IdeMarkService;
import idempotent.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service("ideMarkService")
public class IdeMarkServiceImpl implements IdeMarkService {
    //请求头属性
    private static final String IDEA_MARK = "idea-mark";


    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String createIdeMark() {
        //使用uuid和code拼接为redis的key，code为后端定义的四种类型,code_uuid
        String ideMark = UUID.randomUUID().toString();
        //存入redis 设置过期时间为一天 24 * 60 * 60 * 1000
        boolean flag = redisUtil.setEx(ideMark.toString(), ideMark.toString(), 60*1000);
        if (!flag) {
            throw new ApiException(ApiCode.REPEAT_OPERATION);
        }
        return ideMark;
    }

    @Override
    public boolean checkIdeMark(HttpServletRequest request) {
        //从请球头获取
        String ideMark = request.getHeader(IDEA_MARK);
        // 请求头header中不存在ideMark
        if (StringUtils.isEmpty(ideMark)) {
            throw new ApiException(ApiCode.REPEAT_OPERATION);
            //return false;
        }
        // ideMark从redis查询不到
        if (!redisUtil.exists(ideMark)) {
            throw new ApiException(ApiCode.REPEAT_OPERATION);
            //return false;
        }
        //删除redis中的key
        boolean remove = redisUtil.remove(ideMark);
        if (!remove) {
            throw new ApiException(ApiCode.REPEAT_OPERATION);
            //return false;
        }
        return true;
    }
}
