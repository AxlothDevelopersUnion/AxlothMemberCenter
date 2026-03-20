//数据库接口工厂类
package org.axloth.dao;

public class DAOFactory
{
    public static DAO getDAO()
    {
        return new DAOMySQLImpl();
    }
}
