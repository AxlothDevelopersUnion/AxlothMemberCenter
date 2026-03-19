package org.axloth.frame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
            { //获取当前触发事件的JTree对象并获取最后被选中的结点
                DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
                if(selectedNode==null)
                { //防空指针：若点击空白处或取消选中，可能为空
                    return;
                }
                if(selectedNode.isLeaf())
                { //拦截：只响应叶子结点(没有子结点的底层菜单)的点击
                    String nodeName=selectedNode.getUserObject().toString(); //获取结点上绑定的对象
                    switch(nodeName)
                    {
                        case "档案管理":
                            System.out.println("正在加载[档案管理]面板……");
                            break;
                        case "考勤管理":
                            System.out.println("正在加载[考勤管理]面板……");
                            break;
                        case "奖惩管理":
                            System.out.println("正在加载[奖惩管理]面板……");
                            break;
                        case "培训管理":
                            System.out.println("正在加载[培训管理]面板……");
                            break;
                        case "新增用户":
                            System.out.println("正在加载[新增用户]面板……");
                            break;
                        case "修改密码":
                            System.out.println("正在加载[修改密码]面板……");
                            break;
                        case "打开计算器":
                            try
                            {
                                Runtime.getRuntime().exec("calc");
                            }catch(Exception exception)
                            {
                                System.err.println("打开计算器失败！"+exception.getMessage());
                            }
                            break;
                        case "打开WORD":
                            try
                            {
                                Runtime.getRuntime().exec("cmd start winword");
                            }catch(Exception exception)
                            {
                                System.err.println("打开WORD失败！"+exception.getMessage());
                            }
                            break;
                        case "打开EXCEL":
                            try
                            {
                                Runtime.getRuntime().exec("cmd start excel");
                            }catch(Exception exception)
                            {
                                System.err.println("打开EXCEL失败！"+exception.getMessage());
                            }
                            break;
                        default:
                            System.err.println("选中了一个不存在的模块！");
                            break;
                    }
                }
            }
        });
        final JPanel leftPanel=new JPanel(); //创建导航栏面板

        final JPanel rightPanel=new JPanel(); //创建内容面板

        final JPanel topPanel=new JPanel();

        leftPanel.add(jTree); //将树添加到面板组件中

        final JPanel buttonPanel=new JPanel(); //创建工具栏面板
        final GridLayout gridLayout=new GridLayout(1,0); //创建水平箱式管理器布局
        gridLayout.setVgap(6); //设置箱的垂直间隔为6像素
        gridLayout.setHgap(6);
        buttonPanel.setLayout(gridLayout); //设置工具箱面板采用的布局管理器为箱式布局
        buttonPanel.setBackground(Color.WHITE); //设置工具栏面板的背景色
        //设置工具栏面板采用的边框样式
        buttonPanel.setBorder(new TitledBorder(null,"",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,null,null));
        topPanel.add(buttonPanel,BorderLayout.CENTER); //将工具栏面板添加到上级面板中

        final JButton recordShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        recordShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                rightPanel.add(new RecordSelectedPanel(rightPanel),BorderLayout.CENTER); //将档案管理面板添加到内容面板中
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
            }
        });
        recordShortKeyButton.setText("档案管理");
        buttonPanel.add(recordShortKeyButton);

        final JButton attendanceShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        attendanceShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                rightPanel.add(new AttendanceSelectedPanel(rightPanel),BorderLayout.CENTER); //将档案管理面板添加到内容面板中
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
            }
        });
        attendanceShortKeyButton.setText("考勤管理");
        buttonPanel.add(attendanceShortKeyButton);

        final JButton rewardPunishmentShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        rewardPunishmentShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                rightPanel.add(new RewardPunishmentSelectedPanel(rightPanel),BorderLayout.CENTER); //将档案管理面板添加到内容面板中
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
            }
        });
        rewardPunishmentShortKeyButton.setText("奖惩管理");
        buttonPanel.add(rewardPunishmentShortKeyButton);

        final JButton statsReportShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        statsReportShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                rightPanel.add(new StatsReportSelectedPanel(rightPanel),BorderLayout.CENTER); //将档案管理面板添加到内容面板中
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
            }
        });
        statsReportShortKeyButton.setText("统计报表");
        buttonPanel.add(statsReportShortKeyButton);

        final JButton basicInfoShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        basicInfoShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                rightPanel.add(new BasicInfoSelectedPanel(rightPanel),BorderLayout.CENTER); //将档案管理面板添加到内容面板中
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
            }
        });
        basicInfoShortKeyButton.setText("基本资料");
        buttonPanel.add(basicInfoShortKeyButton);

        final JButton updatePwdShortKeyButton=new JButton(); //创建进入“档案管理”的快捷按钮
        if(record==null)
        {
            updatePwdShortKeyButton.setEnabled(false);
        }
        updatePwdShortKeyButton.addActionListener(new ActionListener()
        { //为按钮创建事件监听器，用来捕获按钮被点击事件
            public void actionPerformed(ActionEvent actionEvent)
            {
                rightPanel.removeAll(); //移除内容面板所有内容
                SwingUtilities.updateComponentTreeUI(rightPanel); //刷新内容面板中的内容
                UpdatePwdDialog dialog=new UpdatePwdDialog(); //创建用来修改密码的对话框
                dialog.setRecord(record); //将当前管理员的档案对象传入对话框
                dialog.setVisible(true); //设置对话框可见，即显示对话框
            }
        });
        updatePwdShortKeyButton.setText("修改密码");
        buttonPanel.add(updatePwdShortKeyButton);

        final JButton calculatorShortcutKeyButton=new JButton();
        calculatorShortcutKeyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    Runtime.getRuntime().exec("calc");
                }catch(Exception exception)
                {
                    System.err.println("打开计算器失败！"+exception.getMessage());
                }
            }
        });
        calculatorShortcutKeyButton.setText("打开计算器");
        buttonPanel.add(calculatorShortcutKeyButton);

        final JButton excelShortcutKeyButton=new JButton();
        excelShortcutKeyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    Runtime.getRuntime().exec("cmd start excel");
                }catch(Exception exception)
                {
                    System.err.println("打开EXCEL失败！"+exception.getMessage());
                }
            }
        });
        excelShortcutKeyButton.setText("打开EXCEL");
        buttonPanel.add(excelShortcutKeyButton);

        final JButton wordShortcutKeyButton=new JButton();
        wordShortcutKeyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    Runtime.getRuntime().exec("cmd start winword");
                }catch(Exception exception)
                {
                    System.err.println("打开WORD失败！"+exception.getMessage());
                }
            }
        });
        wordShortcutKeyButton.setText("打开WORD");
        buttonPanel.add(wordShortcutKeyButton);

        final JButton exitShortcutKeyButton=new JButton();
        exitShortcutKeyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0); //退出系统
            }
        });
        exitShortcutKeyButton.setText("退出");
        buttonPanel.add(exitShortcutKeyButton);
    }
}
