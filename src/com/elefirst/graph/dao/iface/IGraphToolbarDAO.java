package com.elefirst.graph.dao.iface;

import com.elefirst.graph.po.GraphToolbarExample;
import com.elefirst.graph.po.GraphToolbarWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/2/1.
 */
public interface IGraphToolbarDAO {
    List<GraphToolbarWithBLOBs> getGraphToolbarList(GraphToolbarExample example);

    int getGraphToolbarListCount(GraphToolbarExample example);

    int addGraphToolbar(GraphToolbarWithBLOBs template);

    int updateGraphToolbar(GraphToolbarExample example, GraphToolbarWithBLOBs template);

    int delGraphToolbar(GraphToolbarExample example);
}