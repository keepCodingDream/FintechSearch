package com.tracy.task.dbconfig;

import com.cashbus.mybatis.GenericDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created zhoushengsheng on 2016/7/28.
 */
@Repository
public class TaskBaseDao extends GenericDao {
    @Resource
    private SqlSession taskSqlSession;

    @Override
    protected SqlSession getSession() {
        return taskSqlSession;
    }

    @Override
    public String getMapperFullClassName(Class aClass) {
        return "com.db.task.mapper." + aClass.getSimpleName() + "Mapper";
    }

    @Override
    public String getBasePackage() {
        return "com.tracy.task.mapper";
    }

}
