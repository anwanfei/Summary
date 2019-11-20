package com.anfly.summary.db;

import com.anfly.summary.base.SummaryApplication;
import com.anfly.summary.bean.FoodDbBean;
import com.anfly.summary.dao.DaoMaster;
import com.anfly.summary.dao.DaoSession;
import com.anfly.summary.dao.FoodDbBeanDao;

import java.util.List;

/**
 * 配置流程：123
 * 1.一个bean
 * 2.两个工程配置
 * 3.三个项目配置
 */
public class GreendaoUtil {
    private static GreendaoUtil greendaoUtil;
    private final FoodDbBeanDao foodDbBeanDao;

    public GreendaoUtil() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(SummaryApplication.getApp(), "food.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        foodDbBeanDao = daoSession.getFoodDbBeanDao();
    }

    public static GreendaoUtil getGreendaoUtil() {
        if (greendaoUtil == null) {
            synchronized (GreendaoUtil.class) {
                if (greendaoUtil == null) {
                    greendaoUtil = new GreendaoUtil();
                }
            }
        }
        return greendaoUtil;
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public List<FoodDbBean> query() {
        return foodDbBeanDao.queryBuilder().list();
    }

    /**
     * 插入一条数据
     *
     * @param foodDbBean
     * @return
     */
    public long insert(FoodDbBean foodDbBean) {
        if (!isHash(foodDbBean)) {
            long l = foodDbBeanDao.insertOrReplace(foodDbBean);
            return l;
        }
        return -1;
    }

    /**
     * 删除一条数据
     *
     * @param foodDbBean
     * @return
     */
    public boolean delete(FoodDbBean foodDbBean) {
        if (isHash(foodDbBean)) {
            foodDbBeanDao.delete(foodDbBean);
            return true;
        }
        return false;
    }

    /**
     * 修改一条数据
     *
     * @param foodDbBean
     * @return
     */
    public boolean updata(FoodDbBean foodDbBean) {
        if (isHash(foodDbBean)) {
            foodDbBeanDao.update(foodDbBean);
            return true;
        }
        return false;
    }

    /**
     * 查询对应的题目和id
     *
     * @param foodDbBean
     * @return
     */
    public List<FoodDbBean> queryStudent(FoodDbBean foodDbBean) {
        return foodDbBeanDao.queryBuilder().where(FoodDbBeanDao.Properties.Title.eq(foodDbBean.getTitle()), FoodDbBeanDao.Properties.Id.gt(foodDbBean.getId())).list();
    }

    /**
     * 查询多少页
     *
     * @param page
     * @param count
     * @return
     */
    public List<FoodDbBean> queryPage(int page, int count) {
        return foodDbBeanDao.queryBuilder().offset(page * count).limit(count).list();
    }

    /**
     * 模糊查询
     *
     * @param info
     * @return
     */
    public List<FoodDbBean> querLike(String info) {
        List<FoodDbBean> list = foodDbBeanDao.queryBuilder().where(FoodDbBeanDao.Properties.Title.like("%" + info + "%")).list();
        return list;
    }


    /**
     * 判断是否存在
     *
     * @param foodDbBean
     * @return
     */
    public boolean isHash(FoodDbBean foodDbBean) {
        List<FoodDbBean> list = foodDbBeanDao.queryBuilder().where(FoodDbBeanDao.Properties.Id.eq(foodDbBean.getId())).list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
