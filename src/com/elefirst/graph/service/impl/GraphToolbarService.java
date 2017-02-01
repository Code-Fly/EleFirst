package com.elefirst.graph.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.graph.dao.iface.IGraphToolbarDAO;
import com.elefirst.graph.po.GraphToolbarExample;
import com.elefirst.graph.po.GraphToolbarWithBLOBs;
import com.elefirst.graph.service.iface.IGraphToolbarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/1.
 */
@Service
public class GraphToolbarService extends BaseService implements IGraphToolbarService {
    @Autowired
    private IGraphToolbarDAO graphToolbarDAO;

    @Override
    public List<GraphToolbarWithBLOBs> getGraphToolbarList(GraphToolbarWithBLOBs template) {
        GraphToolbarExample condition = new GraphToolbarExample();
        GraphToolbarExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`display_order` ASC");
        return graphToolbarDAO.getGraphToolbarList(condition);
    }

    @Override
    public int getGraphToolbarListCount(GraphToolbarWithBLOBs template) {
        GraphToolbarExample condition = new GraphToolbarExample();
        GraphToolbarExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        return graphToolbarDAO.getGraphToolbarListCount(condition);
    }

    @Override
    public List<GraphToolbarWithBLOBs> getGraphToolbarDetail(String id) {
        GraphToolbarExample condition = new GraphToolbarExample();
        GraphToolbarExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return graphToolbarDAO.getGraphToolbarList(condition);
    }

    @Override
    public int addGraphToolbar(GraphToolbarWithBLOBs template) {
        return graphToolbarDAO.addGraphToolbar(template);
    }

    @Override
    public int updateGraphToolbar(GraphToolbarWithBLOBs template) {
        GraphToolbarExample condition = new GraphToolbarExample();
        GraphToolbarExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return graphToolbarDAO.updateGraphToolbar(condition, template);
    }

    @Override
    public int delGraphToolbar(String id) {
        GraphToolbarExample condition = new GraphToolbarExample();
        GraphToolbarExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return graphToolbarDAO.delGraphToolbar(condition);
    }
}
