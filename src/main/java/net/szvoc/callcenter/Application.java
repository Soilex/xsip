package net.szvoc.callcenter;

import net.szvoc.callcenter.sip.core.net.UdpTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UdpTransport udpTransport;

    @Override
    public void run(String... args) throws Exception {
        udpTransport.open();
    }
}
