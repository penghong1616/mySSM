import com.wsk.bean.SecKillGoodBean;
import com.wsk.dao.SecKillGoodMapper;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.SecKillGood;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@WebAppConfiguration
public class SecKillGoodTest {
    @Autowired
    SecKillGoodMapper secKillGoodMapper;
    @Test
    public void select(){
        List<SecKillGoodBean> secKillGoodBeanList=secKillGoodMapper.selectAll();
        System.out.println(secKillGoodBeanList.size());
    }
    @Test
    public void selectById(){
        SecKillGoodBean secKillGoodBean=secKillGoodMapper.selectSecKillById(1);
        System.out.println(secKillGoodBean.getId());
    }
    @Test
    public void insert(){
        SecKillCar secKillCar=new SecKillCar();
        secKillCar.setUId(2);
        secKillGoodMapper.insert(secKillCar);
    }
}
