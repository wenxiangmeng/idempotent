package idempotent.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 响应结果
 * @anthor theMeng
 * @date 2020/11/01
 */
public class RData<T> implements Serializable {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 0);
        obj.put("msg", 200);
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 0);
        obj.put("msg", 200);
        obj.put("data", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", -1);
        obj.put("msg", "错误");
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", errno);
        obj.put("msg", errmsg);
        return obj;
    }

//    public static Object badArgument() {
//        return fail(401, "参数不对");
//    }
//
//    public static Object badArgumentValue() {
//        return fail(402, "参数值不对");
//    }
//
//    public static Object unlogin() {
//        return fail(501, "请登录");
//    }
//
//    public static Object serious() {
//        return fail(502, "系统内部错误");
//    }
//
//    public static Object unsupport() {
//        return fail(503, "业务不支持");
//    }
//
//    public static Object updatedDateExpired() {
//        return fail(504, "更新数据已经失效");
//    }
//
//    public static Object updatedDataFailed() {
//        return fail(505, "更新数据失败");
//    }
//
//    public static Object unauthz() {
//        return fail(506, "无操作权限");
//    }

}
