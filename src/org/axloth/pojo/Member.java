//实体类——成员类
package org.axloth.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Member
{
    private Long axlothUID; //Axloth成员唯一识别码
    private String name; //成员名称
    private String email; //电子邮箱
    private String telephone; //电话号码
    private String address; //地址
    private String postalCode; //邮政编码
    private String graduateSchool; //毕业院校
    private LocalDate graduateDate; //毕业日期
    private String specialty; //专业
    private String computerSkill; //计算机能力
    private LocalDateTime joinDate; //加入Axloth的日期时间
    private String preference; //喜好
    private String advantage; //优势
    private String disadvantage; //劣势
    private LocalDate birthday; //生日
    private String blogUrl; //博客链接
    private LocalDateTime lastLoginDate; //上次登入日期时间

    public Member()
    {
        super();
    }

    public Member(Long axlothUID,String name,String email,String telephone,String address,String postalCode,String graduateSchool,LocalDate graduateDate,String specialty,
                  String computerSkill,LocalDateTime joinDate,String preference,String advantage,String disadvantage,LocalDate birthday,String blogUrl,LocalDateTime lastLoginDate)
    {
        super();
        this.axlothUID=axlothUID;
        this.name=name;
        this.email=email;
        this.telephone=telephone;
        this.address=address;
        this.postalCode=postalCode;
        this.graduateSchool=graduateSchool;
        this.graduateDate=graduateDate;
        this.specialty=specialty;
        this.computerSkill=computerSkill;
        this.joinDate=joinDate;
        this.preference=preference;
        this.advantage=advantage;
        this.disadvantage=disadvantage;
        this.birthday=birthday;
        this.blogUrl=blogUrl;
        this.lastLoginDate=lastLoginDate;
    }

    public Long getAxlothUID()
    {
        return axlothUID;
    }
    public void setAxlothUID(Long axlothUID)
    {
        this.axlothUID=axlothUID;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getTelephone()
    {
        return telephone;
    }
    public void setTelephone(String telephone)
    {
        this.telephone=telephone;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getPostalCode()
    {
        return postalCode;
    }
    public void setPostalCode(String postalCode)
    {
        this.postalCode=postalCode;
    }
    public String getGraduateSchool()
    {
        return graduateSchool;
    }
    public void setGraduateSchool(String graduateSchool)
    {
        this.graduateSchool=graduateSchool;
    }
    public LocalDate getGraduateDate()
    {
        return graduateDate;
    }
    public void setGraduateDate(LocalDate graduateDate)
    {
        this.graduateDate=graduateDate;
    }
    public String getSpecialty()
    {
        return specialty;
    }
    public void setSpecialty(String specialty)
    {
        this.specialty=specialty;
    }
    public String getComputerSkill()
    {
        return computerSkill;
    }
    public void setComputerSkill(String computerSkill)
    {
        this.computerSkill=computerSkill;
    }
    public LocalDateTime getJoinDate()
    {
        return joinDate;
    }
    public void setJoinDate(LocalDateTime joinDate)
    {
        this.joinDate=joinDate;
    }
    public String getPreference()
    {
        return preference;
    }
    public void setPreference(String preference)
    {
        this.preference=preference;
    }
    public String getAdvantage()
    {
        return advantage;
    }
    public void setAdvantage(String advantage)
    {
        this.advantage=advantage;
    }
    public String getDisadvantage()
    {
        return disadvantage;
    }
    public void setDisadvantage(String disadvantage)
    {
        this.disadvantage=disadvantage;
    }
    public LocalDate getBirthday()
    {
        return birthday;
    }
    public void setBirthday(LocalDate birthday)
    {
        this.birthday=birthday;
    }
    public String getBlogUrl()
    {
        return blogUrl;
    }
    public void setBlogUrl(String blogUrl)
    {
        this.blogUrl=blogUrl;
    }
    public LocalDateTime getLastLoginDate()
    {
        return lastLoginDate;
    }
    public void setLastLoginDate(LocalDateTime lastLoginDate)
    {
        this.lastLoginDate=lastLoginDate;
    }

    @Override
    public int hashCode()
    { //重新方法，只能通过axlothUID生成hash码，维护对象的身份一致性，只要axlothUID不变无论改变其他属性都不会改变身份
        final int prime=31;
        int result=1;
        result=prime*result+((axlothUID==null)?0:axlothUID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
        {
            return true;
        }
        if(obj==null)
        {
            return false;
        }
        if(getClass()!=obj.getClass())
        {
            return false;
        }
        Member member=(Member)obj;
        if(axlothUID==null)
        {
            if(member.axlothUID!=null)
            {
                return false;
            }
        }else if(!axlothUID.equals(member.axlothUID))
        {
            return false;
        }
        return true;
    }
}
