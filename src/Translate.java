import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Translate{
	static File outData=new File("out.txt");
	static BufferedImage bi=null;
	static int threads=8;
	static String[] getStrings=new String[8];
	static int pixNum,pixLength=20,fastestLen;
	
	public static void translate(String path) {
		TranslateInThreads th0=new TranslateInThreads("0");
		TranslateInThreads th1=new TranslateInThreads("1");
		TranslateInThreads th2=new TranslateInThreads("2");
		TranslateInThreads th3=new TranslateInThreads("3");
		TranslateInThreads th4=new TranslateInThreads("4");
		TranslateInThreads th5=new TranslateInThreads("5");
		TranslateInThreads th6=new TranslateInThreads("6");
		TranslateInThreads th7=new TranslateInThreads("7");
		System.out.println("Merge completed.Now translating...");
		System.out.println("Plz wait");
		String b="";
		for(int i=0;i<threads;i++) getStrings[i]="";
		try{
			bi = ImageIO.read(new File(path));
		}catch(Exception e){
			System.out.println(e);
		}
		pixNum=bi.getHeight()*bi.getWidth();
		fastestLen=bi.getWidth()*bi.getWidth()*5*pixLength;
		System.out.println("Width:"+bi.getWidth());
		System.out.println("Height:"+bi.getHeight());
		b="Width:"+bi.getWidth()+"\nHeight:"+bi.getHeight()+"\n";
		th0.start();th1.start();th2.start();th3.start();th4.start();th5.start();th6.start();th7.start();
		while(!IsTransDone()) {
			int cLength=0,tLength=pixNum*pixLength;
			for(int i=0;i<threads;i++) cLength+=getStrings[i].length();
			System.out.printf("\r%10.6f%%",100*(float)cLength/(float)tLength);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int cLength=0,tLength=pixNum*pixLength;
		for(int i=0;i<threads;i++) cLength+=getStrings[i].length();
		System.out.printf("\r%10.6f%%",100*(float)cLength/(float)tLength);
		System.out.println("\ndone");
		for(int i=0;i<getStrings.length;i++) b+=getStrings[i];
		Write(b);
	}
	static boolean IsTransDone() {
		int lengthPT=pixNum*pixLength/threads;
		return getStrings[0].length()==lengthPT&&getStrings[1].length()==lengthPT&&getStrings[2].length()==lengthPT&&getStrings[3].length()==lengthPT&&getStrings[4].length()==lengthPT&&getStrings[5].length()==lengthPT&&getStrings[6].length()==lengthPT&&getStrings[7].length()==lengthPT;
	}
	
	static void Write(String data) {
		try (FileWriter writer = new FileWriter(outData);
				BufferedWriter out = new BufferedWriter(writer)
					) {
				out.write(data); //写存档
				out.flush(); // 把缓存区内容压入文件
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


