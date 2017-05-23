package com.elefirst.graph.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.graph.dao.iface.IGraphTemplateDAO;
import com.elefirst.graph.mapper.GraphTemplateMapper;
import com.elefirst.graph.po.GraphTemplateExample;
import com.elefirst.graph.po.GraphTemplateWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class GraphTemplateDAO extends BaseDAO implements IGraphTemplateDAO {
    @Autowired
    private GraphTemplateMapper graphTemplateMapper;

    @Override
    public List<GraphTemplateWithBLOBs> getGraphTemplateList(GraphTemplateExample example) {
        return graphTemplateMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long getGraphTemplateListCount(GraphTemplateExample example) {
        return graphTemplateMapper.countByExample(example);
    }

    @Override
    public int addGraphTemplate(GraphTemplateWithBLOBs template) {
        return graphTemplateMapper.insertSelective(template);
    }

    @Override
    public int updateGraphTemplate(GraphTemplateExample example, GraphTemplateWithBLOBs template) {
        return graphTemplateMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delGraphTemplate(GraphTemplateExample example) {
        return graphTemplateMapper.deleteByExample(example);
    }
}