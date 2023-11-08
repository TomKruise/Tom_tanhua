package com.tanhua.dubbo.server.api;

import com.tanhua.dubbo.server.pojo.RecommendUser;
import com.tanhua.dubbo.server.vo.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRecommendUserApi {
    @Autowired
    private RecommendUserApi recommendUserApi;

    @Test
    public void testQueryWithMaxScore() {
        Long userId = 2L;
        RecommendUser user = recommendUserApi.queryWithMaxScore(userId);

        if (null != user) {
            System.out.println(user.getScore());
        }
    }

    @Test
    public void testList() {
        PageInfo<RecommendUser> recommendUserPageInfo = recommendUserApi.queryPageInfo(2L, 1, 2);
        System.out.println(recommendUserPageInfo.getRecords().size());
    }
}
