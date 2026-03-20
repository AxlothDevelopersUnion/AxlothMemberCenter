package org.axloth.frame;

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

public class BasicInfoPanel extends JPanel
{
    private JTree deptTree;
    private JTable memberTable;
    private JButton newArchiveBtn;
    private JButton modifyArchiveBtn;

    public BasicInfoPanel()
    {
        setLayout(new BorderLayout());

        //顶部按钮区
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10)); //居中对齐并设置间距

        newArchiveBtn=new JButton("新建成员档案");
        modifyArchiveBtn=new JButton("修改成员档案");
        topPanel.add(newArchiveBtn);
        topPanel.add(modifyArchiveBtn);

        add(topPanel,BorderLayout.NORTH);

        //左侧部门树
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("全部成员");
        root.add(new DefaultMutableTreeNode("经理办公室"));
        root.add(new DefaultMutableTreeNode("销售部"));

        DefaultMutableTreeNode devNode=new DefaultMutableTreeNode("开发部");
        root.add(new DefaultMutableTreeNode("软件开发部"));
        root.add(new DefaultMutableTreeNode("软件测试部"));
        root.add(new DefaultMutableTreeNode("软件设计部"));
        root.add(devNode);

        root.add(new DefaultMutableTreeNode("人事部"));

        deptTree=new JTree(new DefaultTreeModel(root));
        deptTree.expandRow(0); //展开根结点

        JScrollPane treeScrollPane=new JScrollPane(deptTree);
        treeScrollPane.setPreferredSize(new Dimension(100,0)); //设置左侧首选宽度

        //右侧成员数据表
        String[] columnNames={"axlothUID","电子邮件","电话号码","地址","邮政编码","毕业院校","毕业时间","专业","计算机能力","加入联盟的日期时间","喜好","优势","劣势","博客链接","上次登入日期时间"};
        Object[][] data={{}};

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

        JScrollPane tableScrollPane=new JScrollPane(memberTable);
        
        //组装分割面板
        JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeScrollPane,tableScrollPane); //用水平分割面板将树和表格组合
        splitPane.setDividerLocation(150); //设置分割线初始位置
        splitPane.setContinuousLayout(true); //拖动时连续重绘
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
    }
}
