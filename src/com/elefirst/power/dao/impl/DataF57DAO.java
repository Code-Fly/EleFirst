package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.connector.entity.PageResults;
import com.elefirst.power.dao.iface.IDataF57DAO;
import org.springframework.stereotype.Repository;

/**
 * Created by barrie on 17/2/24.
 */
@Repository
public class DataF57DAO extends BaseDAO implements IDataF57DAO {

    @Override
    public PageResults getDataF57List() {
        String hsql = "SELECT t_1488008700240 FROM com.elefirst.power.po.DataF57 t_1488008700240 WHERE ( t_1488008700240.id is not null ) AND ( t_1488008700240.pn between '1' AND '3' ) AND ( t_1488008700240.areaId = '1' )";
        String countHql = "select count(*) from DataF57";
        return findPageByFetchedHql(hsql, countHql, 2, 10);
    }
}
