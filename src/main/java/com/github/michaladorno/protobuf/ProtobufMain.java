package com.github.michaladorno.protobuf;

import com.github.michaladorno.protobuf.simple.Simple.SimpleMessage;
import com.github.michaladorno.protobuf.simple.SimpleEnum;
import com.github.michaladorno.protobuf.simple.SimpleEnum.EnumMessage;

import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello from Protoworld!");

        ////////////////////////////////////////////////////////////////////////////////////////

        //buidling a protobuf object
        final SimpleMessage.Builder builder = SimpleMessage.newBuilder();
        final SimpleMessage simpleMessage = builder.setId(32000)
                .setName("John Smith")
                .setIsSimple(true)
                .addSampleList(1)
                .addSampleList(2)
                .build();

        System.out.println(simpleMessage);
        System.out.println(simpleMessage.getName());

        //writing a protobuf-generated object to a binary file
        FileOutputStream outputStream = new FileOutputStream("simple_message.bin");
        simpleMessage.writeTo(outputStream);

        outputStream.close();

        //convert to an array of bytes (serialization)
        final byte[] justSomeBytes = simpleMessage.toByteArray();

        //parsing an array of bytes (deserialization)
        final SimpleMessage simpleMessageFromBytes = SimpleMessage.parseFrom(justSomeBytes);
        System.out.println(simpleMessage.equals(simpleMessageFromBytes));

        ////////////////////////////////////////////////////////////////////////////////////////

        //using an Enum generated from Protobuf

        final EnumMessage.Builder enumBuilder = EnumMessage.newBuilder();
        enumBuilder.setDayOfTheWeek(SimpleEnum.DayOfTheWeek.SUNDAY);

        final EnumMessage enumMessage = enumBuilder.build();
        System.out.println(enumMessage);

    }
}
