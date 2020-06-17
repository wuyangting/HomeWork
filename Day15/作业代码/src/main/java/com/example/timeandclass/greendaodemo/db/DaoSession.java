package com.example.timeandclass.greendaodemo.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.timeandclass.bean.SearchBean;

import com.example.timeandclass.greendaodemo.db.SearchBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig searchBeanDaoConfig;

    private final SearchBeanDao searchBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        searchBeanDaoConfig = daoConfigMap.get(SearchBeanDao.class).clone();
        searchBeanDaoConfig.initIdentityScope(type);

        searchBeanDao = new SearchBeanDao(searchBeanDaoConfig, this);

        registerDao(SearchBean.class, searchBeanDao);
    }
    
    public void clear() {
        searchBeanDaoConfig.clearIdentityScope();
    }

    public SearchBeanDao getSearchBeanDao() {
        return searchBeanDao;
    }

}
