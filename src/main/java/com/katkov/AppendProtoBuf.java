package com.katkov;

import com.katkov.anchortext.proto.AnchorTextProto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AppendProtoBuf {

    public static final String FILE_NAME = "test.protobuf";

    //http://stackoverflow.com/questions/9148878/google-protocol-buffers-storing-messages-into-file
    public static void main(String... args) throws IOException {

        OutputStream outputStream = new FileOutputStream(FILE_NAME);
        for (int i = 0; i < 10; i++) {
            AnchorTextProto.AnchorText.Builder builder = AnchorTextProto.AnchorText.newBuilder().setUrl(
                    "new stuff" + i);
            builder.build().writeDelimitedTo(outputStream);
        }
        outputStream.flush();
        outputStream.close();


        //To read the entire file:
        InputStream inputStream = new FileInputStream(FILE_NAME);
        AnchorTextProto.AnchorText anchorText = AnchorTextProto.AnchorText.parseDelimitedFrom(inputStream);
        while (anchorText != null) {
            System.out.println(anchorText);
            anchorText = AnchorTextProto.AnchorText.parseDelimitedFrom(inputStream);
        }
        inputStream.close();

    }

}
