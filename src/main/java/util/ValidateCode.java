package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ValidateCode {
	private int width = 160 ;
	//图片宽
	private int height = 40;
	//图片高
	private int codeCount = 5;
	//验证码字符个数
	private String code = null;
	//生成的验证码
	private BufferedImage bufferedImage = null; 
	//将图片加载到内存
	//要操作一张图，必须先将它从磁盘加载到内存中，然后才能对图片进行进一步处理
	private char[] codeSequence = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'
			,'U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
			,'1','2','3','4','5','6','7','8','9','0'};
	
	public ValidateCode(){
		
	}
	public ValidateCode(int width, int height){
		this.width = width;
		this.height = height;
	}
	/*
	 * @param width 图片宽
	 * @param height 图片高
	 * @param codeCount 验证码产生的字符个数
	 * @return
	 * */
	public ValidateCode(int width, int height, int codeCount){
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
	}
	
	public void createCode(){
		int x=0;
		int fontHeight =0;//文字高度
		int codeY =0;
		int red=0, green=0, blue=0;
		
		x = this.width/(this.codeCount+2);
		fontHeight = this.height - 2;
		codeY = this.height - 4;
		
		this.bufferedImage = new BufferedImage(codeY, height, 1);
		Graphics2D g = this.bufferedImage.createGraphics();
		//Graphics2D 用于呈现二维图像、形状和文本
		Random random = new Random();
		////生成字母作为验证码
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);
		ImgFontByte imgFontByte = new ImgFontByte();
		Font font = imgFontByte.getFont(fontHeight);
		g.setFont(font);
		
		//绘制随机线
		for(int i=0; i<codeCount*2; i++){
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width/8);
			int ye = ys + random.nextInt(height/8);
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			g.drawLine(xs, ys, xe, ye); 
		}
		
		StringBuffer randomCode = new StringBuffer();
		for(int i=0; i<codeCount; i++){
			String strRandom = String.valueOf(codeSequence[random.nextInt()]);
			//为每个字设置随机颜色
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			g.drawString(strRandom, (i+1)*x, codeY);
			randomCode.append(strRandom);
		}
		code = randomCode.toString();
		//将产生的验证码转化为字符串用于与输入的验证码校验
	}
	public void write(String path) throws IOException{
		OutputStream ops = new FileOutputStream(path);
		this.write(ops); 
	}
	public void write(OutputStream ops) throws IOException{
		// TODO Auto-generated method stub
		ImageIO.write(bufferedImage, "png", ops);
		ops.close();
	}
	public BufferedImage getBuffImg(){
		return bufferedImage;
	} 
	public String getCode(){
		return code;
	}
}
