import com.wsk.tool.StringUtils;
import org.junit.Test;

public class MDTest {
    @Test
    public void test(){
        StringUtils stringUtils=StringUtils.getInstance();
        System.out.println(stringUtils.getMD5("123"));
    }
}
