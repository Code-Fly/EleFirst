package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.connector.entity.PageResults;
import com.elefirst.connector.example.HibernateExample;
import com.elefirst.power.dao.iface.IDataF57DAO;
import com.elefirst.power.po.DataF57;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/24.
 */
@Repository
public class DataF57DAO extends BaseDAO<DataF57, Serializable> implements IDataF57DAO {

    @Override
    public PageResults getDataF57List() {
        HibernateExample condition = new HibernateExample(DataF57.class);
        HibernateExample.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("id");
        criteria.andBetween("pn", "1", "3");
        criteria.andEqualTo("areaId", "1");
        List<Object> list = new ArrayList<>();
        list.add("417");
        list.add("3658");
        criteria.andIn("concentratorId", list);
        condition.setPage(2);
        condition.setRows(10);
        condition.setOrderByClause("sendTime ASC");

        System.out.println(JSONObject.fromObject(get("8a9df2e85a6af901015a6b0ed3d10086")).toString());

        return findPageByExample(condition);
    }
}
