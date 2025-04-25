package com.abc;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MetricsInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DefaultExports.initialize();
        sce.getServletContext().addServlet("metrics", new MetricsServlet())
            .addMapping("/metrics");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Optional cleanup
    }
}
