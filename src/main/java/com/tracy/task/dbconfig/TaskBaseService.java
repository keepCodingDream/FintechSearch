package com.tracy.task.dbconfig;

import com.cashbus.mybatis.GenericDao;
import com.cashbus.mybatis.GenericService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created zhoushengsheng on 2016/7/28.
 */
@Service
public class TaskBaseService extends GenericService implements ITaskBaseService {
    @Resource
    private TaskBaseDao zhugeBaseDao;

    @Override
    public GenericDao getGenericDao() {
        return zhugeBaseDao;
    }

}
