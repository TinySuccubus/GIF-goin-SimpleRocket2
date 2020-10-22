import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImageMerge{
	static String p1=null;
	static String out=null;
	static int progr;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//ImageMerge im=new ImageMerge();
		//im.run();
		Scanner sc=new Scanner(System.in);
		System.out.println("Start from(5digits,like:00000):");
		String sf=sc.next();
		System.out.println("Again(number only):");
		int startfrom=sc.nextInt();
		p1="img//IMG"+sf+".bmp";
		System.out.println("Merge times:");
		int picNum=progr=sc.nextInt();
		String p2=null;
		System.out.println("Starting");
		out="merged.bmp";
		if(picNum==-1) {
			BufferedImage bi1=null;
			try {
				bi1=getBufferedImage("img//"+sf+".bmp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			generateSaveFile(bi1,"img//out//"+out,-1);
		}else
			for(int a=startfrom,counter=1;a<picNum+startfrom;a++,counter++){
				if(a<10){
					p2="img//IMG0000"+a+".bmp";
				}else if(a<100){
					p2="img//IMG000"+a+".bmp";
				}else if(a<1000){
					p2="img//IMG00"+a+".bmp";
				}else if(a<10000){
					p2="img//IMG0"+a+".bmp";
				}else if(a<100000) {
					p2="img//IMG"+a+".bmp";
				}
				run(p1,p2,"img//out//"+out,counter);
			}
		Translate.translate("img//out//"+out);
	}

	public static void run(String saveFilePath,String divingPath,String margeImagePath,int c){
        // 读取待合并的文件
        BufferedImage bi1 = null;
        BufferedImage bi2 = null;
        // 调用mergeImage方法获得合并后的图像
        BufferedImage destImg = null;
        try {
            bi1 = getBufferedImage(saveFilePath);
            bi2 = getBufferedImage(divingPath);
            // 调用mergeImage方法获得合并后的图像
            destImg = mergeImage(bi1, bi2, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 保存图像
        generateSaveFile(destImg, margeImagePath,c);
		/*synchronized(this){
			this.notify();
		}*/
    }

	public static BufferedImage getBufferedImage(String fileUrl) throws IOException {
        File f = new File(fileUrl);
        return ImageIO.read(f);
    }    
    

	public static BufferedImage getBufferedImageDestUrl(String destUrl) {
        HttpURLConnection conn = null;
        BufferedImage image = null;
        try {
            URL url = new URL(destUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200) {
                image = ImageIO.read(conn.getInputStream());
                return image;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return image;
    }


	public static void generateSaveFile(BufferedImage buffImg, String savePath,int c) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            File outFile = new File(savePath);
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            ImageIO.write(buffImg, savePath.substring(temp), outFile);
            System.out.print("\rMerging..."+c+"/"+progr+"="+(((float)c/progr)*100)+"%");
        } catch (IOException e) {
            e.printStackTrace();
        }
		p1="img//out//"+out;
    }


	public static BufferedImage mergeImage(BufferedImage img1,BufferedImage img2, boolean isHorizontal) throws IOException {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);
        BufferedImage DestImage = null;
        if (isHorizontal) {
            DestImage = new BufferedImage(w1+w2, h1, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
        } else {
            DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2);
        }
		return DestImage;
	}
}