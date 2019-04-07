package ttms._test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import ttms._dao.IFilmdao;
import ttms._dao._impl.FilmdaoImpl;

public class TestForImage {
		/**
		 * 传递图片路径，返回该图片的字节数组
		 * 	如果图片路径错误，则返回null
		 * 
		 * @param imagePath
		 * @return
		 */
	public static byte[] getByteArrayImage(String imagePath) {
		byte[] byteArry = null;
		try {
			//得到图片路径
			File file1 = new File(imagePath);
			//将图片读取到图片缓冲区
			BufferedImage bufferedImage = ImageIO.read(file1);
			//申请一个字节数组封装类
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			//将图片缓冲区内容填充给字节数组封装类
			ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
			byteArry = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			byteArry = null;		
		}
		return byteArry;
	}
	
	/**
	 * 通过引用二进制数组，和保存图片的路径，将二进制数组保存为图片
	 * @param byteArry
	 * @param imagePath
	 * @throws IOException
	 */
	public static void setImageOfByteArry(byte[] byteArry, String imagePath) throws IOException {
			File file = new File(imagePath);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(byteArry);
			fileOutputStream.close();
	}
	
	@org.junit.Test
	public void test() {
		IFilmdao dao = new FilmdaoImpl();
		byte[] arry = TestForImage.getByteArrayImage(dao.get(1L).getPosterPath());
		try {
			TestForImage.setImageOfByteArry(arry, "C:\\Users\\hanjiangbo\\Desktop\\算法 A\\test.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
	//File file2 = new File("C:\\Users\\lenovo\\Desktop\\text1.jpg");
	//FileOutputStream fileOutputStream = new FileOutputStream(file2);
	//fileOutputStream.write(byte1);
	//ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byte1);
	//BufferedImage bufferedImage2 = ImageIO.read(byteArrayInputStream);
	//File file2 = new File("C:\\Users\\lenovo\\Desktop\\text.jpg");
	//ImageIO.write(bufferedImage2, "jpg", file2);


