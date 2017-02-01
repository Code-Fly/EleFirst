package com.elefirst.graph.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.graph.dao.iface.IGraphToolbarDAO;
import com.elefirst.graph.mapper.GraphToolbarMapper;
import com.elefirst.graph.po.GraphToolbarExample;
import com.elefirst.graph.po.GraphToolbarWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/1.
 */
@Repository
public class GraphToolbarDAO extends BaseDAO implements IGraphToolbarDAO {
    @Autowired
    private GraphToolbarMapper graphToolbarMapper;

    @Override
    public List<GraphToolbarWithBLOBs> getGraphToolbarList(GraphToolbarExample example) {
        return graphToolbarMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int getGraphToolbarListCount(GraphToolbarExample example) {
        return graphToolbarMapper.countByExample(example);
    }

    @Override
    public int addGraphToolbar(GraphToolbarWithBLOBs template) {
        return graphToolbarMapper.insertSelective(template);
    }

    @Override
    public int updateGraphToolbar(GraphToolbarExample example, GraphToolbarWithBLOBs template) {
        return graphToolbarMapper.updateByExampleWithBLOBs(template, example);
    }

    @Override
    public int delGraphToolbar(GraphToolbarExample example) {
        return graphToolbarMapper.deleteByExample(example);
    }
}
