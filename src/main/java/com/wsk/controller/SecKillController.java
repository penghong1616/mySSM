package com.wsk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsk.bean.SecKillGoodBean;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.UserInformation;
import com.wsk.response.BaseResponse;
import com.wsk.service.SecKillGoodService;
import com.wsk.tool.Constant;
import com.wsk.tool.JUtil;
import com.wsk.tool.JedisUtil;
import com.wsk.tool.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class SecKillController implements InitializingBean {
    @Autowired
    private SecKillGoodService secKillGoodService;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //跳转到秒杀页面
    @RequestMapping("secKill")
    public String secKill(HttpServletRequest request,Model model){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInformation");
        // if user login,the session will have the "userInformation"
        if (!StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            model.addAttribute("userInformation", userInformation);
        } else {
            userInformation = new UserInformation();
            model.addAttribute("userInformation", userInformation);
        }

        return "secKill/secKill";
    }
    @RequestMapping("secKillAll")
    @ResponseBody
    public PageInfo selectAll(@RequestParam(required = false,defaultValue = "0") String pageNum){
        PageHelper.offsetPage(Integer.parseInt(pageNum),4);
        PageInfo<SecKillGoodBean> pageInfo=new PageInfo<>(secKillGoodService.selectAll());
        return pageInfo;
    }
    @RequestMapping("selectSecKillById")
    public String selectSecKillById( HttpServletRequest request,Model model,int id){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInformation");
        // if user login,the session will have the "userInformation"
        if (!StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            model.addAttribute("userInformation", userInformation);
        } else {
            userInformation = new UserInformation();
            model.addAttribute("userInformation", userInformation);
        }
        SecKillGoodBean secKillGoodBean=secKillGoodService.selectSecKillById(id);
        //有开始秒杀时间和结束时间
        long startTime=secKillGoodBean.getStartDate().getTime();
        long endTime=secKillGoodBean.getEndDate().getTime();
        long now=System.currentTimeMillis();
        int secKillStatus=0;
        int remainSeconds=0;
        if (now<startTime){//秒杀未开始
            secKillStatus=0;
            remainSeconds=(int)(startTime-now);
        }else if(now>endTime){//秒杀已经结
            secKillStatus=2;
            remainSeconds=-1;
        }else{                 //秒杀正在进行
            secKillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("seckillStatus", secKillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secKillGoodBean",secKillGoodBean);
        return "secKill/secKillGoodDetail";
    }
    //根据secId秒杀，返回JSON格式，根据状态码商品状态和是否已经秒杀成功
    @RequestMapping("secKillGood")
    @ResponseBody
    public BaseResponse secKillGood(@RequestParam("id") int id,HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInformation");
        if (userInformation==null){
            return BaseResponse.NOT_LOGIN;
        }
        //查询Redis数据库该商品数量
        int secKillGoodCount=Integer.parseInt(jedisUtil.get(Constant.SECKILLGOODID+id));
        if (secKillGoodCount==0){
            return BaseResponse.fail(0);
        }
        int userId=userInformation.getId();
        if(jedisUtil.exists(Constant.SECKILLUSERID+userId+"-"+id)){
            return BaseResponse.REPLEAT_SECKILL;
        }
        SecKillCar secKillCar=new SecKillCar();
        secKillCar.setModified(new Date());
        secKillCar.setUId(userId);//用户ID
        secKillCar.setSecId(id);//秒杀商品ID
        jedisUtil.set(Constant.SECKILLUSERID+userId+"-"+id,1);
        jedisUtil.expire(Constant.SECKILLUSERID+userId+"-"+id,24*60*60*1000);//设置过期时间
        // 将秒杀信息发送到队列中去
        rabbitTemplate.convertAndSend(Constant.SECKILLKEY,JUtil.objToString(secKillCar));
        return BaseResponse.success();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<SecKillGoodBean>  secKillGoodBeanList=secKillGoodService.selectAll();
        if (secKillGoodBeanList==null)return ;
        for (SecKillGoodBean secKillGoodBean:secKillGoodBeanList){
            jedisUtil.set(Constant.SECKILLGOODID+secKillGoodBean.getId(),secKillGoodBean.getCount());
        }
    }
}
