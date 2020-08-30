package com.sample.controller;

import ai.yue.library.base.util.DateUtils;
import ai.yue.library.base.util.UUIDUtils;
import ai.yue.library.base.view.Result;
import ai.yue.library.base.view.ResultInfo;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sample.entity.Demo;
import com.sample.mapper.DemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    DemoRepository demoRepository;

    @GetMapping("/create")
    public Result<Demo> hello() {

        Demo demo = new Demo();
        demo.setName(RandomUtil.randomString(6));
        demo.setPwd(RandomUtil.randomString(8));

        demoRepository.insert(demo);

        return ResultInfo.success(demo);
    }


    @GetMapping("/update")
    public Result<Demo>  update(@RequestParam Long id,String pwd) {
        Demo demo = demoRepository.selectById(id);
        if (demo != null) {
            demo.setPwd(pwd==null?"empty_pwd":pwd);
            demoRepository.updateById(demo);

            return ResultInfo.success(demo);
        }
        return ResultInfo.error(null);
    }

    @GetMapping("/list")
    public Result<IPage<Demo>>  list(Integer p) {

        MDC.put("rid", UUIDUtils.lowerCaseUUID());

        if (p == null || p <= 1) {

            p  = 1;
        }
        IPage<Demo> page = new Page<Demo>(p, 2, true);
        Date baseDate = DateUtils.parseDate("2020-08-28 10:00:00");
        IPage<Demo> pageResult = demoRepository.selectPage(page, new QueryWrapper<Demo>().lambda().gt(Demo::getCreateTime, baseDate));

        log.info("日志中打印rid");

        MDC.remove("rid");

        return ResultInfo.success(pageResult);
    }
}
