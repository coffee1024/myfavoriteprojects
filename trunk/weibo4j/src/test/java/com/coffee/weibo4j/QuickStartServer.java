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

			if (scanIntervalSeconds > 0) {
				Scanner scanner1 = new Scanner("target/test-classes", scanIntervalSeconds,server) {
					public void onChange() {
						try {
							System.err.println("\nLoading test-classes changes ......");
							JettyFactory.reloadContext(this.server);
							System.err.println("Loading complete.");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} 
				};
				Scanner scanner2 = new Scanner("target/classes", scanIntervalSeconds,server) {
					public void onChange() {
						try {
							System.err.println("\nLoading classes changes ......");
							JettyFactory.reloadContext(this.server);
							System.err.println("Loading complete.");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} 
				};
				System.out.println("Starting scanner at interval of " + scanIntervalSeconds + " seconds.");
				scanner1.start();
				scanner2.start();
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
