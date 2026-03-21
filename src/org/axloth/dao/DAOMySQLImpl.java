package org.axloth.dao;

import org.axloth.pojo.Member;
import org.axloth.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DAOMySQLImpl implements DAO
{
    Connection conn=null;
    Statement stmt=null;
    PreparedStatement pStmt=null;
    ResultSet resultSet=null;

    @Override
    public Set<Member> getAllMember()
    {
        Set<Member> set=new HashSet<>();
        String sql="SELECT axlothUID, name, email, telephone, address, postalcode, graduate_school, graduate_date, specialty, computer_skill, join_date, preference,"+
                " advantage, disadvantage, birthday, blog_url, last_login_date FROM axloth_member_center.tb_member_info";
        conn=JDBCUtil.getConnection();
        try
        {
            stmt=conn.createStatement(); //创建执行SQL语句的接口
            resultSet=stmt.executeQuery(sql);
            while(resultSet.next())
            {
                Long axlothUID=resultSet.getLong("axlothUID");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String telephone=resultSet.getString("telephone");
                String address=resultSet.getString("address");
                String postalCode=resultSet.getString("postalCode");
                String graduateSchool=resultSet.getString("graduate_school");
                LocalDate graduateDate=resultSet.getObject("graduate_date",LocalDate.class);
                String specialty=resultSet.getString("specialty");
                String computerSkill=resultSet.getString("computer_skill");
                LocalDateTime joinDate=resultSet.getObject("join_date",LocalDateTime.class);
                String preference=resultSet.getString("preference");
                String advantage=resultSet.getString("advantage");
                String disadvantage=resultSet.getString("disadvantage");
                LocalDate birthday=resultSet.getObject("birthday",LocalDate.class);
                String blogUrl=resultSet.getString("blog_url");
                LocalDateTime lastLoginDate=resultSet.getObject("last_login_date",LocalDateTime.class);
                Member member=new Member(axlothUID,name,email,telephone,address,postalCode,graduateSchool,graduateDate,specialty,computerSkill,joinDate,preference,advantage,disadvantage,birthday,blogUrl,lastLoginDate);
                set.add(member);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            JDBCUtil.close(stmt,pStmt,resultSet);
        }
        return set;
    }
}
