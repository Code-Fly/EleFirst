package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataT031;
import com.elefirst.power.po.DataT031Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataT031DAO {
    List<DataT031> getDataT031List(DataT031Example example);

    long getDataT031ListCount(DataT031Example example);

    int addDataT031(DataT031 template);

    int updateDataT031(DataT031Example example, DataT031 template);

    int delDataT031(DataT031Example example);
}
