package ir.blujr.protobufdemo.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;

public class ProtobufUtil {

    /*public static Struct fromJsonToProtobuf(String json) throws InvalidProtocolBufferException {
        Struct.Builder builder = Struct.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        Struct protobuf = builder.build();
        return protobuf;

    }*/

    /*public static String fromProtobufToJson(Message protobuf) throws InvalidProtocolBufferException {
        return JsonFormat.printer()
                .includingDefaultValueFields()
                .omittingInsignificantWhitespace()
                .print(protobuf);

    }*/

    public static Message fromJson(String json) throws InvalidProtocolBufferException {
        Message.Builder structBuilder = Struct.newBuilder();
        JsonFormat.parser()
                .ignoringUnknownFields()
                .merge(json, structBuilder);
        return structBuilder.build();
    }

    public static String toJson(MessageOrBuilder messageOrBuilder) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(messageOrBuilder);
    }
}
