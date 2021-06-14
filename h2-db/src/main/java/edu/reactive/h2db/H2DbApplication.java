package edu.reactive.h2db;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@SpringBootApplication
public class H2DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2DbApplication.class, args);
	}

	@Component
	@Slf4j
	public static class H2Server {

		private Server webServer;
		private static volatile boolean started;

		@Value("${spring.h2.port:5432}")
		private int h2Port;

		@EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
		public void start() {
			if (!started) {
				log.info("STARTING H2 DEDICATED SERVER ON PORT: " + h2Port);
				try {
					this.webServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(h2Port), "-ifNotExists", "-trace").start();
					started = true;
				} catch (SQLException e) {
					log.error("COULD NOT START H2 DEDICATED SERVER! ", e);
				}
			} else {
				log.info("H2 DEDICATED SERVER ALREADY STARTED ON PORT: " + h2Port);
			}
		}

		@EventListener(org.springframework.context.event.ContextClosedEvent.class)
		public void stop() {
			log.info("STOPPING H2 DEDICATED SERVER");
			if (this.webServer != null) {
				this.webServer.stop();
				started = false;
			}
		}

	}
}
