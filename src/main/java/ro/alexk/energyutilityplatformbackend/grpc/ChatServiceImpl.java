package ro.alexk.energyutilityplatformbackend.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@GRpcService
public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

    Map<String, StreamObserver<Message>> subscriptions = new HashMap<>();

    @Override
    public void subscribeToChat(Subscription request, StreamObserver<Message> responseObserver) {
        log.info(request.getId() + " has subscribed to the chat.");
        subscriptions.put(request.getId(), responseObserver);
    }

    @Override
    public void unsubscribeFromChat(Subscription request, StreamObserver<nothing> responseObserver) {
        log.info(request.getId() + " has unsubscribed to the chat.");
        var stream = subscriptions.remove(request.getId());
        if (stream == null) return;
        stream.onCompleted();
    }

    @Override
    public void sendMessage(ChatMessage request, StreamObserver<nothing> responseObserver) {
        log.info(request.getFrom() + " has messaged " + request.getTo());
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.CHAT_MESSAGE)
                        .setChatMessage(request)
                        .build()
        );
    }

    @Override
    public void sendTypingNotification(TypingNotification request, StreamObserver<nothing> responseObserver) {
        log.info(request.getFrom() + " is typing to " + request.getTo());
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.TYPING_NOTIFICATION)
                        .setTypingNotification(request)
                        .build()
        );
    }

    @Override
    public void sendMessageReadNotification(MessageReadNotification request, StreamObserver<nothing> responseObserver) {
        log.info(request.getFrom() + " read messages from " + request.getTo());
        subscriptions.get(request.getTo()).onNext(
                Message.newBuilder()
                        .setMessageType(MessageType.MESSAGE_READ_NOTIFICATION)
                        .setMessageReadNotification(request)
                        .build()
        );
    }
}
