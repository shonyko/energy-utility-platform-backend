//package ro.alexk.energyutilityplatformbackend.grpc;
//
//import io.grpc.ServerBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Slf4j
//@Service
//public class ChatServiceCommandLine implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) {
//        var server = ServerBuilder
//                .forPort(9090)
//                .addService(new ChatServiceImpl())
//                .build();
//        try {
//            server.start();
//            log.info("gRPC server started successfully!");
//            server.awaitTermination();
//        } catch (IOException e) {
//            log.error("Could not bind gRPC server to port 9090: " + e.getMessage());
//        } catch (InterruptedException e) {
//            log.error("gRPC server was stopped: " + e.getMessage());
//        }
//    }
//}
