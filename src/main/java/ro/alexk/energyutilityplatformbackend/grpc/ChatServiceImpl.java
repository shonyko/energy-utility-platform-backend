package ro.alexk.energyutilityplatformbackend.grpc;

import io.grpc.stub.StreamObserver;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public void sendMessage(Message request, StreamObserver<Message> responseObserver) {
        System.out.println("Got: " + request.getMessage());
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void sendTypingNotification(TypingNotification request, StreamObserver<nothing> responseObserver) {
        super.sendTypingNotification(request, responseObserver);
    }

    @Override
    public void sendMessageReadNotification(MessageReadNotification request, StreamObserver<nothing> responseObserver) {
        super.sendMessageReadNotification(request, responseObserver);
    }
}
