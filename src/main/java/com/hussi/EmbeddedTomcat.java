package com.hussi;

import com.hussi.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcat {

    public static void main1(String[] args) throws Exception {
        // Create an instance of Tomcat server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Set the base directory to a temporary folder
        tomcat.setBaseDir("temp");

        // Add a web application context
        Context context = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());

        // Add the servlet
        Tomcat.addServlet(context, "helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello", "helloServlet");

        // Start the server
        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Connector connector1 = tomcat.getConnector();
        connector1.setPort(8080);
        Connector connector2 = new Connector();
        connector2.setPort(8090);

        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();

        Context ctx = tomcat.addContext(contextPath,docBase);
        String urlPattern = "/hello";
        String servletName = "helloServlet";

        tomcat.addServlet(contextPath, servletName, new HelloServlet());
        ctx.addServletMappingDecoded(urlPattern, servletName);


//        Tomcat.addServlet(ctx, servletName, new HelloServlet());
//        ctx.addServletMapping("/hello", "myServlet");
//        ctx.addServletMappingDecoded(urlPattern, servletName);

        tomcat.start();
        tomcat.getService().addConnector(connector1);
        tomcat.getService().addConnector(connector2);
        tomcat.getServer().await();
    }
}