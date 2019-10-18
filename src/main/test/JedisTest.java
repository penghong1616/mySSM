import com.wsk.pojo.SecKillGood;
import com.wsk.tool.JedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@WebAppConfiguration
public class JedisTest {
    @Autowired
    JedisUtil jedisUtil;
    @Test
    public void redisConnect(){
        System.out.println(jedisUtil.set("name","ph"));
        System.out.println(jedisUtil.get("name"));
        SecKillGood secKIllGood=new SecKillGood();
        secKIllGood.setId(1);
        secKIllGood.setCount(1);
        secKIllGood.setDisplay(1);
        jedisUtil.set("sec",secKIllGood);
        System.out.println("secId:"+jedisUtil.get("sec",SecKillGood.class).getId());
    }
}
