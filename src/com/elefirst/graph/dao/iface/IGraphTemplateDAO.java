package com.elefirst.graph.dao.iface;

import com.elefirst.graph.po.GraphTemplateExample;
import com.elefirst.graph.po.GraphTemplateWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IGraphTemplateDAO {
    List<GraphTemplateWithBLOBs> getGraphTemplateList(GraphTemplateExample example);

    long getGraphTemplateListCount(GraphTemplateExample example);

    int addGraphTemplate(GraphTemplateWithBLOBs template);

    int updateGraphTemplate(GraphTemplateExample example, GraphTemplateWithBLOBs template);

    int delGraphTemplate(GraphTemplateExample example);
}
