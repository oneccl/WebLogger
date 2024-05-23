package com.cc.weblogger.service;

import com.cc.weblogger.dao.SysLogDao;
import com.cc.weblogger.pojo.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/2/28
 * Time: 11:49
 * Description:
 */

@Service
public class SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    public Boolean addLog(SysLog log){
        return sysLogDao.insert(log) > 0;
    }

}
