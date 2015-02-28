import com.hx.service.RequestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xh on 2015/2/28.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        RequestService requestService = (RequestService)context.getBean("requestService");


        requestService.queryIndexList(null);
    }
}
