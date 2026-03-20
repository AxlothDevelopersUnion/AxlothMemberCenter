package org.axloth.dao;

import org.axloth.pojo.Member;

import java.util.Set;

public interface DAO
{
    /**
     * 获取所有成员
     * @return 所有成员对象集合
     */
    public Set<Member> getAllMember();
}
