package froop.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {

  public static void main(String[] args) {
    JettyServer server = new JettyServer(8080);
    try {
      server.start();
      server.join();
    } finally {
      server.stop();
    }
  }

  private final Server server;
  private final WebAppContext context;

  public JettyServer(int port) {
    this.server = new Server(port);
    this.context = new WebAppContext();
    context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
    context.setResourceBase("webapp");
//    setupRest();
    server.setHandler(context);
  }

//  private void setupRest() {
//    ResourceConfig config = new ResourceConfig()
//        .packages("froop.rest.resource")
//        .register(JacksonFeature.class);
//    ServletHolder holder =  new ServletHolder(new ServletContainer(config));
//    holder.setInitOrder(1);
//    context.addServlet(holder, "/api/*");
//  }

  public void start() {
    try {
      server.start();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public void join() {
    try {
      server.join();
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
