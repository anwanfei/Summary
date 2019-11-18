package com.anfly.summary.utils;

import com.anfly.geek.base.BaseApplication;
import com.anfly.geek.bean.DailyNewsDbBean;
import com.anfly.geek.dao.DailyNewsDbBeanDao;
import com.anfly.geek.dao.DaoMaster;
import com.anfly.geek.dao.DaoSession;

import java.util.List;

/**
 * @author Anfly
 * @date 2019/7/15
 * description：
 */
public class DbUtil {
    private volatile static DbUtil dbUtil;
    private DailyNewsDbBeanDao dailyNewsDbBeanDao;

    public static DbUtil getDbUtil() {
        if (dbUtil == null) {
            synchronized (DbUtil.class) {
                if (dbUtil == null) {
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    private DbUtil() {
        //1.创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getApplication(), "news.db");
        //2.获取读写对象
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //3.获取管理器类
        DaoSession daoSession = daoMaster.newSession();
        //4.获取表对象
        dailyNewsDbBeanDao = daoSession.getDailyNewsDbBeanDao();
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public List<DailyNewsDbBean> query() {
        return dailyNewsDbBeanDao.queryBuilder().list();
    }

    /**
     * 插入一条数据
     *
     * @param dailyNewsDbBean
     * @return
     */
    public long insert(DailyNewsDbBean dailyNewsDbBean) {
        if (!isHash(dailyNewsDbBean)) {
            long l = dailyNewsDbBeanDao.insertOrReplace(dailyNewsDbBean);
            return l;
        }
        return -1;
    }

    /**
     * 删除一条数据
     *
     * @param dailyNewsDbBean
     * @return
     */
    public boolean delete(DailyNewsDbBean dailyNewsDbBean) {
        if (isHash(dailyNewsDbBean)) {
            dailyNewsDbBeanDao.delete(dailyNewsDbBean);
            return true;
        }
        return false;
    }

    /**
     * 修改一条数据
     *
     * @param dailyNewsDbBean
     * @return
     */
    public boolean updata(DailyNewsDbBean dailyNewsDbBean) {
        if (isHash(dailyNewsDbBean)) {
            dailyNewsDbBeanDao.update(dailyNewsDbBean);
            return true;
        }
        return false;
    }

    /**
     * 查询对应的题目和id
     *
     * @param dailyNewsDbBean
     * @return
     */
    public List<DailyNewsDbBean> queryStudent(DailyNewsDbBean dailyNewsDbBean) {
        return dailyNewsDbBeanDao.queryBuilder().where(DailyNewsDbBeanDao.Properties.Title.eq(dailyNewsDbBean.getTitle()), DailyNewsDbBeanDao.Properties.Id.gt(dailyNewsDbBean.getId())).list();
    }

    /**
     * 查询多少页
     *
     * @param page
     * @param count
     * @return
     */
    public List<DailyNewsDbBean> queryPage(int page, int count) {
        return dailyNewsDbBeanDao.queryBuilder().offset(page * count).limit(count).list();
    }

    /**
     * 模糊查询
     *
     * @param info
     * @return
     */
    public List<DailyNewsDbBean> querLike(String info) {
        List<DailyNewsDbBean> list = dailyNewsDbBeanDao.queryBuilder().where(DailyNewsDbBeanDao.Properties.Title.like("%" + info + "%")).list();
        return list;
    }


    /**
     * 判断是否存在
     *
     * @param dailyNewsDbBean
     * @return
     */
    public boolean isHash(DailyNewsDbBean dailyNewsDbBean) {
        List<DailyNewsDbBean> list = dailyNewsDbBeanDao.queryBuilder().where(DailyNewsDbBeanDao.Properties.Id.eq(dailyNewsDbBean.getId())).list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
