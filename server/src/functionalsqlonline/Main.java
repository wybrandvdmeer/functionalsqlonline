package functionalsqlonline;

import functionalsqlonline.server.FunctionalSQLOnlineServer;

public class Main {
	public static void main(String [] args) throws Exception {
        FunctionalSQLOnlineServer server = new FunctionalSQLOnlineServer();
        server.start();
	}
}
