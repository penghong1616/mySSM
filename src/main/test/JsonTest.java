import com.wsk.pojo.SecKillGood;
import com.wsk.tool.JUtil;
import org.junit.Test;

public class JsonTest {
    @Test
    public void toStirng(){
        SecKillGood secKIllGood=new SecKillGood();
        secKIllGood.setId(1);
        secKIllGood.setCount(1);
        secKIllGood.setDisplay(1);
        System.out.println(JUtil.objToString(secKIllGood));
    }
}
