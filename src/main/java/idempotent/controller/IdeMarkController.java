package idempotent.controller;

import idempotent.annotation.Idempotent;
import idempotent.api.RData;
import idempotent.service.IdeMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis 使用幂等性
 */
@RestController
@RequestMapping("/ide/mark")
public class IdeMarkController {

    @Autowired
    private IdeMarkService ideMarkService;

    /**
     * 创建幂等性标识
     * @return
     */
    @GetMapping("/createIdeMark")
    public Object createIdeMark(){
        return RData.ok(ideMarkService.createIdeMark());
    }

    /**
     * 测试幂等性
     * @return
     */
    @Idempotent
    @GetMapping("/test/Idempotence")
    public Object testIdempotence() {
        String ideMark = "幂等测试成功";
        return RData.ok(ideMark) ;
    }
}
