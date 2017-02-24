package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.base.entity.PageResults;
import com.elefirst.power.dao.iface.IDataF57DAO;
import org.springframework.stereotype.Repository;

/**
 * Created by barrie on 17/2/24.
 */
@Repository
public class DataF57DAO extends BaseDAO implements IDataF57DAO {

    @Override
    public PageResults getDataF57List() {
        String hsql = "from DataF57";
        String countHql = "select count(*) from DataF57";
        return findPageByFetchedHql(hsql, countHql, 2, 10);
    }
}
