package me.mritd.swing;
/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                         佛祖保佑        永无BUG
 *              佛曰:  
 *                     写字楼里写字间，写字间里程序员；  
 *                     程序人员写程序，又拿程序换酒钱。  
 *                     酒醒只在网上坐，酒醉还来网下眠；  
 *                     酒醉酒醒日复日，网上网下年复年。  
 *                     但愿老死电脑间，不愿鞠躬老板前；  
 *                     奔驰宝马贵者趣，公交自行程序员。  
 *                     别人笑我忒疯癫，我笑自己命太贱；  
 *                     不见满街漂亮妹，哪个归得程序员？  
 *                     							code by 漠然
*/
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import me.mritd.utils.BareBonesBrowserLaunch;
import me.mritd.utils.CertUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
/**
 * 
 * Copyright © 2016 Mritd. All rights reserved.
 * @ClassName: SSLCertTool
 * @Description: TODO SSL 证书获取工具类
 * @author: 漠然
 * @date: 2016年3月31日 下午1:36:41
 */
public class SSLCertFrame extends JFrame {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO 序列化ID
	 * 
	 */
	private static final long serialVersionUID = 7567037105941992894L;
	private final Logger logger = Logger.getLogger(SSLCertFrame.class);
	private JMenu about;
	private JMenuItem homePage;
	private JMenuItem blog;
	private JLabel lblHttps1;
	private JLabel lblHttps2;
	private JLabel lblHttps13;
	private JLabel lblHttps14;
	private JLabel lb_httpsAddress;
	private JButton biubiubiu;
	private JTextField httpsAddress;
	
	// 证书存储路径
	public static final String CERT_PATH = System.getProperty("user.dir")+File.separator+"SSLCertTool"+File.separator+"jssecacerts";
	
	
	/**
	 * 
	 * @Title main
	 * @Description TODO 主方法
	 * @throws  
	 * @return void
	 * @param args
	 * @author 漠然
	 * @date 2016年3月31日 下午1:36:41
	 */
	public static void main(String[] args) {
		
		// 初始化样式
		try{
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			UIManager.put("RootPane.setupButtonVisible", false);
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }catch(Exception e){
	    	Logger.getLogger(SSLCertFrame.class).error("BeautyEye初始化失败,异常信息: ", e);
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SSLCertFrame frame = new SSLCertFrame();
					frame.setLocationRelativeTo(null);	//窗口正中央显示
					frame.setVisible(true);				//显示窗口
					frame.init(); 	 					//其他信息初始化
				} catch (Exception e) {
					Logger.getLogger(SSLCertFrame.class).error("异常信息: ", e);
				}
			}
		});
		Logger.getLogger(SSLCertFrame.class).info("++++++ 程序启动!");
	}
	
	/**
	 * 
	 * @Title: init
	 * @Description: TODO 初始化方法
	 * @return: void
	 */
	public void init(){
		
	}
	
	/**
	 * 
	 * <p>Title: 默认构造方法</p> 
	 * <p>Description: 窗体初始化 及相关组件初始化</p>
	 */
	public SSLCertFrame() {
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 14));
		
		// 不可调整大小
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(SSLCertFrame.class.getResource("/me/mritd/image/logo.png")));
		setTitle("SSL证书工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 487);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		about = new JMenu("关于");
		about.setIcon(new ImageIcon(SSLCertFrame.class.getResource("/me/mritd/image/menu_about.png")));
		about.setFont(new Font("华文新魏", Font.PLAIN, 16));
		menuBar.add(about);
		
		homePage = new JMenuItem("项目主页");
		homePage.setIcon(new ImageIcon(SSLCertFrame.class.getResource("/me/mritd/image/menu_homepage.png")));
		homePage.setFont(new Font("华文新魏", Font.PLAIN, 14));
		homePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("####### 访问项目主页: http://git.oschina.net/D.k/SSLCertTool");
				BareBonesBrowserLaunch.openURL("http://git.oschina.net/D.k/SSLCertTool");
			}
		});
		
		about.add(homePage);
		blog = new JMenuItem("个人博客");
		blog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("####### 访问个人博客: http://mritd.me");
				BareBonesBrowserLaunch.openURL("http://mritd.me");
			}
		});
		
		blog.setIcon(new ImageIcon(SSLCertFrame.class.getResource("/me/mritd/image/menu_blog.png")));
		blog.setFont(new Font("华文新魏", Font.PLAIN, 14));
		about.add(blog);
		
		lblHttps1 = new JLabel("本工具用于对指定 HTTPS 地址的 SSL 证书生成 jssecacerts ");
		lblHttps1.setFont(new Font("华文新魏", Font.PLAIN, 16));
		
		lblHttps2 = new JLabel("生成的信任文件 jssecacerts 位于 SSLCertTool 目录下");
		lblHttps2.setFont(new Font("华文新魏", Font.PLAIN, 16));
		
		lblHttps13 = new JLabel("将此文件复制到 %JAVA_HOME%/jre/lib/security/ 目录下即可");
		lblHttps13.setFont(new Font("华文新魏", Font.PLAIN, 16));
		
		lblHttps14 = new JLabel("此操作将使当前 JDK 运行的 Java 程序信任该 HTTPS SSL 证书");
		lblHttps14.setFont(new Font("华文新魏", Font.PLAIN, 16));
		
		lb_httpsAddress = new JLabel("HTTPS 地址：https://hostname[:port][:passwd]");
		lb_httpsAddress.setFont(new Font("华文新魏", Font.PLAIN, 16));
		
		httpsAddress = new JTextField();
		httpsAddress.setColumns(10);
		
		biubiubiu = new JButton("发射(biu~biu~biu~)");
		biubiubiu.setFont(new Font("华文新魏", Font.PLAIN, 16));
		biubiubiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logger.info("###### 发射  biu~  biu~  biu~");
				 
				new SwingWorker<Boolean, Boolean>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						try {
							CertUtils certUtils = new CertUtils();
							
							String url = httpsAddress.getText();
							
							if (StringUtils.isBlank(url)) {
								return false;
							}
							url = url.substring(url.indexOf("//")+2);
							
							logger.info("URL截取: "+url);
							
							String[] param = url.split(":");
							if (param.length==1) {
								certUtils.createCert(param[0], null, null);
							}else if (param.length==2) {
								certUtils.createCert(param[0], param[1], null);
							}else if (param.length==3){
								certUtils.createCert(param[0], param[1], param[2]);
							}else{
								logger.error("XXXXXX 地址解析出错！");
								return false;
							}
						} catch (Exception e) {
							logger.error("XXXXXX 创建错误: ",e);
							return false;
						}
						
						return true;
					}


					@Override
					protected void done() {
						super.done();
						try {
							Boolean flag = get();
							if (flag) {
								JOptionPane.showMessageDialog(getContentPane(), "创建 jssecacerts 成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(getContentPane(), "创建 jssecacerts 失败!", "失败", JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException  e) {
							logger.error("XXXXXX 获取状态出错: ",e);
						} catch (ExecutionException e) {
							logger.error("XXXXXX 获取状态出错: ",e);
						}
					}

					
					
				}.execute();
			}
		});
		biubiubiu.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(145, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHttps14)
								.addComponent(lblHttps13)
								.addComponent(lblHttps2)
								.addComponent(lblHttps1, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE))
							.addGap(132))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(httpsAddress, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
							.addGap(158))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(197)
					.addComponent(lb_httpsAddress)
					.addContainerGap(187, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(264)
					.addComponent(biubiubiu)
					.addContainerGap(311, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(lblHttps1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblHttps2)
					.addGap(18)
					.addComponent(lblHttps13)
					.addGap(18)
					.addComponent(lblHttps14)
					.addGap(45)
					.addComponent(lb_httpsAddress)
					.addGap(18)
					.addComponent(httpsAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(biubiubiu)
					.addContainerGap(114, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
