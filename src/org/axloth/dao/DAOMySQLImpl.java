package org.axloth.dao;

import org.axloth.pojo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        String sql="SELECT axlothUID, name, email, telephone, address, postalcode, graduate_school, graduate_date, specialty, computer_skill, join_date, preference," +
                " advantage, disadvantage, birthday, blog_url, last_login_date FROM axloth_member_center.tb_member_info";

        return set;
    }
}
