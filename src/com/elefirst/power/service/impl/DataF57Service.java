package com.elefirst.power.service.impl;

import com.elefirst.base.entity.PageResults;
import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF57DAO;
import com.elefirst.power.service.iface.IDataF57Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by barrie on 17/2/24.
 */
@Service
public class DataF57Service extends BaseService implements IDataF57Service {
    @Autowired
    private IDataF57DAO dataF57DAO;

    @Override
    public PageResults getDataF57List() {
        return dataF57DAO.getDataF57List();
    }
}
