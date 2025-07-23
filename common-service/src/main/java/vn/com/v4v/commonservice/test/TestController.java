package vn.com.v4v.commonservice.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.commonservice.service.IMasterDataService;

@RestController
public class TestController {

    private final IMasterDataService iMasterDataService;

    @Autowired
    public TestController(IMasterDataService iMasterDataService) {
        this.iMasterDataService = iMasterDataService;
    }

    @GetMapping("/api/v1/test")
    public String sayHello() {

        iMasterDataService.test();
        return "Hello World";
    }
}
