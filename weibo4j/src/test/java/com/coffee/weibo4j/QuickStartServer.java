package com.coffee.weibo4j;

import org.eclipse.jetty.server.Server;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车快速重新加载应用.
 * 
 * @author calvin
 */
public class QuickStartServer {

	public static final int PORT = 8080;
	public static final int scanIntervalSeconds = 10;
	public static final String CONTEXT = "/weibo";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web",
			"springside-core" };

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);
		try {
			server.start();

			System.out.println("[INFO] Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("[HINT] Hit Enter to reload the application quickly");

			// 等待用户输入回车重载应用.
			if (scanIntervalSeconds > 0) {
				Scanner scanner = new Scanner(PathKit.getRootClassPath(), scanIntervalSeconds,server) {
					public void onChange() {
						try {
							System.err.println("\nLoading changes ......");
							JettyFactory.reloadContext(this.server);
							System.err.println("Loading complete.");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
				};
				System.out.println("Starting scanner at interval of " + scanIntervalSeconds + " seconds.");
				scanner.start();
			}
//			while (true) {
//				char c = (char) System.in.read();
//				if (c == '\n') {
//					JettyFactory.reloadContext(server);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
