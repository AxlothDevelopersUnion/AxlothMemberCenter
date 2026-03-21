package org.axloth.frame;

import org.axloth.dao.DAO;
import org.axloth.dao.DAOFactory;
import org.axloth.pojo.Member;
import org.axloth.session.Session;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BasicInfoPanel extends JPanel
{
    private JTree deptTree;
    private JTable memberTable;
    private JButton newArchiveBtn;
    private JButton modifyArchiveBtn;

    private static DAO dao=DAOFactory.getDAO(); //数据库接口

    public static Object[][] loadAllMember()
    { //加载所有成员
        Session.MEMBER_HASH_SET.clear(); //重置全局会话中的成员缓存，防止数据重叠
        Session.MEMBER_HASH_SET.addAll(dao.getAllMember()); //从数据库中加载所有成员的对象集

        Object[][] data=new Object[Session.MEMBER_HASH_SET.size()][17];
        Iterator<Member> iterator=Session.MEMBER_HASH_SET.iterator();
        int i=0;
        Member m=new Member();

        while(iterator.hasNext())
        {
            m=iterator.next();
            data[i][0]=m.getAxlothUID();
            data[i][1]=m.getName();
            data[i][2]=m.getEmail();
            data[i][3]=m.getTelephone();
            data[i][4]=m.getAddress();
            data[i][5]=m.getPostalCode();
            data[i][6]=m.getGraduateSchool();
            data[i][7]=m.getGraduateDate();
            data[i][8]=m.getSpecialty();
            data[i][9]=m.getComputerSkill();
            data[i][10]=m.getJoinDate();
            data[i][11]=m.getPreference();
            data[i][12]=m.getAdvantage();
            data[i][13]=m.getDisadvantage();
            data[i][14]=m.getBirthday();
            data[i][15]=m.getBlogUrl();
            data[i][16]=m.getLastLoginDate();
            ++i;
        }
        return data;
    }

    public BasicInfoPanel()
    {
        setLayout(new BorderLayout());
        setBounds(0,0,1450,850);

        //顶部按钮区
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10)); //居中对齐并设置间距

        newArchiveBtn=new JButton("新建成员档案");
        modifyArchiveBtn=new JButton("修改成员档案");
        topPanel.add(newArchiveBtn);
        topPanel.add(modifyArchiveBtn);
        topPanel.setBounds(0,0,1450,50);
        add(topPanel,BorderLayout.NORTH);

        //左侧部门树
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("全部成员");
        root.add(new DefaultMutableTreeNode("经理办公室"));
        root.add(new DefaultMutableTreeNode("销售部"));

        DefaultMutableTreeNode devNode=new DefaultMutableTreeNode("开发部");
        devNode.add(new DefaultMutableTreeNode("软件开发部"));
        devNode.add(new DefaultMutableTreeNode("软件测试部"));
        devNode.add(new DefaultMutableTreeNode("软件设计部"));
        root.add(devNode);

        root.add(new DefaultMutableTreeNode("人事部"));

        deptTree=new JTree(new DefaultTreeModel(root));
        deptTree.expandRow(0); //展开根结点

        JScrollPane treeScrollPane=new JScrollPane(deptTree);
        treeScrollPane.setPreferredSize(new Dimension(100,0)); //设置左侧首选宽度

        //右侧成员数据表
        String[] columnNames={"axlothUID","姓名","电子邮件","电话号码","地址","邮政编码","毕业院校","毕业时间","专业","计算机能力","加入Axloth的日期时间","喜好","优势","劣势","生日","博客链接","上次登入日期时间"};
        Object[][] data=loadAllMember();

        DefaultTableModel tableModel=new DefaultTableModel(data,columnNames)
        {
            @Override
            public boolean isCellEditable(int row,int column)
            { //表格双击后不可直接编辑，必须通过特定按钮
                return false;
            }
        };

        memberTable=new JTable(tableModel);
        memberTable.getTableHeader().setFont(new Font("黑体",Font.PLAIN,14));
        memberTable.setRowHeight(25);
        memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        memberTable.getColumnModel().getColumn(2).setPreferredWidth(110); //“电子邮件”的列宽
        memberTable.getColumnModel().getColumn(3).setPreferredWidth(110); //“电话号码”的列宽
        memberTable.getColumnModel().getColumn(6).setPreferredWidth(110); //“毕业院校”的列宽
        memberTable.getColumnModel().getColumn(10).setPreferredWidth(150); //“加入Axloth的日期时间”的列宽
        memberTable.getColumnModel().getColumn(14).setPreferredWidth(150); //“上次登入日期时间”的列宽

        JScrollPane tableScrollPane=new JScrollPane(memberTable);

        //组装分割面板
        JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeScrollPane,tableScrollPane); //用水平分割面板将树和表格组合
        splitPane.setDividerLocation(150); //设置分割线初始位置
        splitPane.setContinuousLayout(true); //拖动时连续重绘
        splitPane.setBounds(0,50,1450,800);
        add(splitPane,BorderLayout.CENTER); //将分割面板添加到中央

        newArchiveBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.out.println("触发[新增成员档案]按钮");
            }
        });
        modifyArchiveBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.out.println("触发[修改成员档案]按钮");
            }
        });

        deptTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            public void valueChanged(TreeSelectionEvent treeSelectionEvent)
            {
                System.out.println("触发[切换左侧树]选择事件");
            }
        });

        revalidate();
        repaint();
    }
}
