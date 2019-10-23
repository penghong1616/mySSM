import com.wsk.cache.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@WebAppConfiguration
public class RedisCacheTest {
    @Autowired
    private RedisCache redisCache;
    @Test
    public void testRedisCache(){
        redisCache.putObject("ph123","ph123");
    }

}
