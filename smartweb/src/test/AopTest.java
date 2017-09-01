import com.wzj.helper.BeanHelper;
import com.wzj.service.SystemService;
import org.junit.Test;

public class AopTest {

    @Test
    public void testAop(){
        try {
            Class.forName("com.wzj.helper.AopHelper");
            SystemService systemService = (SystemService) BeanHelper.objectMap.get(SystemService.class);
            systemService.printLog();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
