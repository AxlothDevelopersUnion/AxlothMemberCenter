package org.axloth.frame;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;

public class MainFrame
{
    public static void main(String[] args)
    {
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("root"); //创建树的结点
        DefaultMutableTreeNode personnelNode=new DefaultMutableTreeNode("人事管理"); //创建树的一级子结点
        personnelNode.add(new DefaultMutableTreeNode("档案管理")); //创建树的叶子结点并添加到一级子结点
        personnelNode.add(new DefaultMutableTreeNode("考勤管理"));
        personnelNode.add(new DefaultMutableTreeNode("奖惩管理"));
        personnelNode.add(new DefaultMutableTreeNode("培训管理"));
        root.add(personnelNode); //向根结点添加一级子结点
        DefaultMutableTreeNode treatmentNode=new DefaultMutableTreeNode("待遇管理");
        treatmentNode.add(new DefaultMutableTreeNode("账套管理"));
        treatmentNode.add(new DefaultMutableTreeNode("人员设置"));
        treatmentNode.add(new DefaultMutableTreeNode("统计报表"));
        root.add(treatmentNode);
        DefaultMutableTreeNode systemNode=new DefaultMutableTreeNode("系统维护");
        systemNode.add(new DefaultMutableTreeNode("企业架构"));
        systemNode.add(new DefaultMutableTreeNode("基本资料"));
        systemNode.add(new DefaultMutableTreeNode("初始化系统"));
        root.add(systemNode);
        DefaultMutableTreeNode userNode=new DefaultMutableTreeNode("用户管理");
        if(record==null)
        { //当record为null时，说明是通过默认用户登入的，此时只能新增用户，不能修改密码
            userNode.add(new DefaultMutableTreeNode("新增用户"));
        }else
        {
            String purview=record.getTbManager().getPurview();
            if(purview.equals("管理员"))
            { //只有当管理员的权限为“管理员”时，才有权修改密码
                userNode.add(new DefaultMutableTreeNode("新增用户"));
                userNode.add(new DefaultMutableTreeNode("修改密码"));
            }
        }
        root.add(userNode);
        DefaultMutableTreeNode toolNode=new DefaultMutableTreeNode("系统工具");
        toolNode.add(new DefaultMutableTreeNode("打开计算器"));
        toolNode.add(new DefaultMutableTreeNode("打开WORD"));
        toolNode.add(new DefaultMutableTreeNode("打开EXCEL"));
        root.add(toolNode);
        DefaultTreeModel treeModel=new DefaultTreeModel(root); //通过树结点对象创建树模型对象

        JTree jTree=new JTree(treeModel); //通过树模型对象创建树对象
        jTree.setBackground(Color.WHITE); //设置树的背景色
        jTree.setRootVisible(false); //设置不显示树的根结点
        jTree.setRowHeight(28); //设置各结点的高度为28像素
        Font font=new Font("黑体",Font.BOLD,16);
        jTree.setFont(font); //设置结点的字体样式
        DefaultTreeCellRenderer renderer=new DefaultTreeCellRenderer(); //创建一个树的绘制对象
        renderer.setClosedIcon(null); //设置结点折叠时不采用图标
        renderer.setOpenIcon(null); //设置结点展开时不采用图标
        jTree.setCellRenderer(renderer); //将树的绘制对象设置到树中
        int count=root.getChildCount(); //获得一级结点的数量
        for(int i=0;i<count;++i)
        {
            DefaultMutableTreeNode tNode=(DefaultMutableTreeNode)root.getChildAt(i); //获得指定索引的一级结点对象
            TreePath tPath=new TreePath(tNode.getPath()); //获得结点对象的路径
            jTree.expandPath(tPath); //展开该结点
        }
        jTree.addTreeSelectionListener(new TreeSelectionListener()
        { //捕获树的选取事件
            public void valueChanged(TreeSelectionEvent e)
            {

            }
        });
        leftPanel.add(jTree);
    }
}
