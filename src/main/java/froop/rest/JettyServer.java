package froop.rest;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

class JettyServer {

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
    context.setResourceBase("web");
    setupRest();
    server.setHandler(context);
  }

  private void setupRest() {
    ResourceConfig config = new PackagesResourceConfig("froop.rest.resource");
    config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
   ServletHolder holder =  new ServletHolder(new ServletContainer(config));
    holder.setInitOrder(1);
    context.addServlet(holder, "/api/*");
  }

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
