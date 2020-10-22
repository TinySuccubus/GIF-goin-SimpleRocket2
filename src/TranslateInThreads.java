import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class TranslateInThreads extends Thread{
	private Thread t;
	private String threadName;
	private int num;
	TranslateInThreads(String name){
		threadName=name;
		num=Integer.parseInt(threadName);
		System.out.println("Creating "+threadName);
	}
	
	public void run() {
		System.out.println("Running "+threadName);
		DecimalFormat df=new DecimalFormat("0.0000");
		BufferedImage bi=Translate.bi;
		float[] r=null;
		int ymax=(num+1)*(bi.getHeight()/Translate.threads);
		int ymin=num*(bi.getHeight()/Translate.threads);
		int xmax=bi.getWidth();
		String a="";
		for(int y=ymin;y<ymax;y++) {
			for(int x=0;x<xmax;x++) {
				try{
					r=getRGB(bi,x,y);
				}catch(Exception e){
					System.out.println(e);
				}
				a+=df.format((float)r[0])+","+df.format((float)r[1])+","+df.format((float)r[2]);
			}
			if((y+1)%5==0) {
				Translate.getStrings[num]+=a;
				a="";
			}else if(y+1==ymax) {
				Translate.getStrings[num]+=a;
				a="";
			}
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}
	public void start() {
		System.out.println("Starting " +  threadName);
		if(t==null) {
			t=new Thread(this,threadName);
			t.start();
		}
	}
	float[] getRGB(BufferedImage image, int x, int y)  throws IOException{
		int[] rgb = null;
		if (image != null && x < image.getWidth() && y < image.getHeight()) {
			rgb = new int[3];
			int pixel = image.getRGB(x, y);
			rgb[0] = (pixel & 0xff0000) >> 16;
			rgb[1] = (pixel & 0xff00) >> 8;
			rgb[2] = (pixel & 0xff);
		}
		float[] re=new float[]{(float)rgb[0]*0.0039f,(float)rgb[1]*0.0039f,(float)rgb[2]*0.0039f};
		return re;
	}
}
