package com.elefirst.graph.service.iface;

import com.elefirst.graph.po.GraphTemplateWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IGraphTemplateService {
    List<GraphTemplateWithBLOBs> getGraphTemplateList(GraphTemplateWithBLOBs template);

    int getGraphTemplateListCount(GraphTemplateWithBLOBs template);

    List<GraphTemplateWithBLOBs> getGraphTemplateDetail(String id);

    int addGraphTemplate(GraphTemplateWithBLOBs template);

    int updateGraphTemplate(GraphTemplateWithBLOBs template);

    int delGraphTemplate(String id);
}
