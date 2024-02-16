package ir.blujr.protobufdemo;

import com.google.protobuf.InvalidProtocolBufferException;
import ir.blujr.protobufdemo.util.ProtobufUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProtobufDemoApplication {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		SpringApplication.run(ProtobufDemoApplication.class, args);

		/*String customerJson = "{\n" +
				"    \"id\":123,\n" +
				"    \"name\":\"sara\",\n" +
				"    \"email\":\"sara@gmail.com\"\n" +
				"}";

		System.out.println(ProtobufUtil.fromJson(customerJson));*/


	}

}
