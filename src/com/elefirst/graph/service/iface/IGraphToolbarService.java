package com.elefirst.graph.service.iface;

import com.elefirst.graph.po.GraphToolbarWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/2/1.
 */
public interface IGraphToolbarService {
    List<GraphToolbarWithBLOBs> getGraphToolbarList(GraphToolbarWithBLOBs template);

    int getGraphToolbarListCount(GraphToolbarWithBLOBs template);

    List<GraphToolbarWithBLOBs> getGraphToolbarDetail(String id);

    int addGraphToolbar(GraphToolbarWithBLOBs template);

    int updateGraphToolbar(GraphToolbarWithBLOBs template);

    int delGraphToolbar(String id);
}