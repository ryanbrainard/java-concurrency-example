import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final ExecutorService executor = Executors.newCachedThreadPool();

        for (final char c : "Hello from Java!".toCharArray()) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        resp.getWriter().println(Thread.currentThread() + ":\t" + String.valueOf(c));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();   
    }
}
