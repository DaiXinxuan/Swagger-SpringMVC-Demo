package pins.philips.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 310231492 on 2016/4/13.
 */
@Controller
@RequestMapping("/test")
@Api(value = "test")
public class TestClass {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value = "Test Method", response = Integer.class, notes = "Test")
    public int testMethod() {
        return 1;
    }
}
