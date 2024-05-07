package com.tanhua.dubbo.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.tanhua.dubbo.server.pojo.RecommendUser;
import com.tanhua.dubbo.server.pojo.Visitors;
import com.tanhua.dubbo.server.vo.PageInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service(version = "1.0.0")
public class VisitorsApiImpl implements VisitorsApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveVisitor(Visitors visitors) {
        visitors.setId(ObjectId.get());
        visitors.setDate(System.currentTimeMillis());

        this.mongoTemplate.save(visitors);
        return visitors.getId().toHexString();
    }

    @Override
    public List<Visitors> topVisitor(Long userId, Integer num) {
        Pageable pageable = PageRequest.of(0, num, Sort.by(Sort.Order.desc("date")));
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageable);
        return this.queryVisitorsList(query);
    }

    @Override
    public List<Visitors> topVisitor(Long userId, Long date) {
        Query query = Query.query(Criteria
                .where("userId")
                .is(userId)
                .and("date").lt(date)).with(Sort.by(Sort.Order.desc("date")));
        return this.queryVisitorsList(query);
    }

    @Override
    public PageInfo<Visitors> topVisitor(Long userId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("date")));
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageable);
        List<Visitors> visitorsList = this.queryVisitorsList(query);

        PageInfo<Visitors> pageInfo = new PageInfo<>();

        pageInfo.setRecords(visitorsList);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(page);
        pageInfo.setTotal(0); //暂时不提供

        return pageInfo;
    }

    private List<Visitors> queryVisitorsList(Query query) {
        List<Visitors> visitorsList = this.mongoTemplate.find(query, Visitors.class);

        for (Visitors visitors : visitorsList) {

            Query recommendQuery = Query.query(Criteria
                    .where("userId").is(visitors.getVisitorUserId())
                    .and("toUserId").is(visitors.getUserId()));
            RecommendUser recommendUser = this.mongoTemplate.findOne(recommendQuery, RecommendUser.class);
            if(null != recommendUser){
                visitors.setScore(recommendUser.getScore());
            }else{
                //设置的默认值
                visitors.setScore(30d);
            }
        }

        return visitorsList;
    }
}
