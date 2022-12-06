package ro.alexk.energyutilityplatformbackend.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.HashMap;
import java.util.Map;

@GRpcService
public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

    Map<String, StreamObserver<Message>> subscriptions = new HashMap<>();

    @Override
    public void subscribeToChat(Subscription request, StreamObserver<Message> responseObserver) {
        subscriptions.put(request.getId(), responseObserver);
    }

    @Override
    public void unsubscribeFromChat(Subscription request, StreamObserver<nothing> responseObserver) {
        var stream = subscriptions.remove(request.getId());
        if (stream == null) return;
        stream.onCompleted();
    }

    @Override
    public void sendMessage(ChatMessage request, StreamObserver<nothing> responseObserver) {
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.CHAT_MESSAGE)
                        .setChatMessage(request)
                        .build()
        );
    }

    @Override
    public void sendTypingNotification(TypingNotification request, StreamObserver<nothing> responseObserver) {
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.TYPING_NOTIFICATION)
                        .setTypingNotification(request)
                        .build()
        );
    }

    @Override
    public void sendMessageReadNotification(MessageReadNotification request, StreamObserver<nothing> responseObserver) {
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.MESSAGE_READ_NOTIFICATION)
                        .setMessageReadNotification(request)
                        .build()
        );
    }
}
