package com.elefirst.graph.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.graph.dao.iface.IGraphTemplateDAO;
import com.elefirst.graph.po.GraphTemplateExample;
import com.elefirst.graph.po.GraphTemplateWithBLOBs;
import com.elefirst.graph.service.iface.IGraphTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class GraphTemplateService extends BaseService implements IGraphTemplateService {
    @Autowired
    private IGraphTemplateDAO graphTemplateDAO;

    @Override
    public List<GraphTemplateWithBLOBs> getGraphTemplateList(GraphTemplateWithBLOBs template) {
        GraphTemplateExample condition = new GraphTemplateExample();
        GraphTemplateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return graphTemplateDAO.getGraphTemplateList(condition);
    }

    @Override
    public long getGraphTemplateListCount(GraphTemplateWithBLOBs template) {
        GraphTemplateExample condition = new GraphTemplateExample();
        GraphTemplateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        return graphTemplateDAO.getGraphTemplateListCount(condition);
    }

    @Override
    public List<GraphTemplateWithBLOBs> getGraphTemplateDetail(String id) {
        GraphTemplateExample condition = new GraphTemplateExample();
        GraphTemplateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return graphTemplateDAO.getGraphTemplateList(condition);
    }

    @Override
    public int addGraphTemplate(GraphTemplateWithBLOBs template) {
        return graphTemplateDAO.addGraphTemplate(template);
    }

    @Override
    public int updateGraphTemplate(GraphTemplateWithBLOBs template) {
        GraphTemplateExample condition = new GraphTemplateExample();
        GraphTemplateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return graphTemplateDAO.updateGraphTemplate(condition, template);
    }

    @Override
    public int delGraphTemplate(String id) {
        GraphTemplateExample condition = new GraphTemplateExample();
        GraphTemplateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return graphTemplateDAO.delGraphTemplate(condition);
    }
}
