package ttms._test;

import org.junit.Test;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

import ttms._util.SendMessage;

public class TestForsendMessage {
	@Test
	public void send() throws ServerException, ClientException {
		String s = SendMessage.send("15829336653");
		System.out.println(s);
	}
}
