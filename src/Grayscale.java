import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Grayscale {

    public static void main(String[] args) throws IOException {
        
        BufferedImage inputImage = ImageIO.read(new File("E:/Users/Joseph/Pictures/2980/KeepOnTruckin.png"));
        
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth() * 2,  2*inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        BufferedImage outputImageR = new BufferedImage(inputImage.getWidth() * 2,  2*inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        BufferedImage outputImageG = new BufferedImage(inputImage.getWidth() * 2,  2*inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        BufferedImage outputImageB = new BufferedImage(inputImage.getWidth() * 2,  2*inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        BufferedImage outputImageF = new BufferedImage(inputImage.getHeight() * 2,  2*inputImage.getWidth(), BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0; y < outputImage.getHeight();  y++)
        {
            for(int x = 0; x < outputImage.getWidth(); x++)
            {
                float x_old = x/2;
                float y_old = y/2;
                
                int pixelFirst = inputImage.getRGB((int)x_old, (int)y_old );
                int pixelSecond =  inputImage.getRGB(
                        Math.min((int)x_old + 1, inputImage.getWidth() - 1), 
                       (int)y_old  );
                
                Color cFirst = new Color(pixelFirst);
                Color cSecond = new Color(pixelSecond);
                
                float iValue = (float)x/2.0f -  (int)(x/2);
                
                int iRed = (int) ((1 - iValue) * cFirst.getRed() + iValue * cSecond.getRed());
                int iGreen = (int) ((1 - iValue) * cFirst.getGreen() + iValue * cSecond.getGreen());
                int iBlue = (int) ((1 - iValue) * cFirst.getBlue() + iValue * cSecond.getBlue());
                
                int gray = (iRed + iGreen + iBlue) / 3;
                
                Color iColor = new Color(iRed, iRed, iRed);
                int iPixel = iColor.getRGB();
                outputImageR.setRGB(x, y, iPixel);
                
                iColor = new Color(iGreen, iGreen, iGreen);
                iPixel = iColor.getRGB();
                outputImageG.setRGB(x, y, iPixel);
                
                iColor = new Color(iBlue, iBlue, iBlue);
                iPixel = iColor.getRGB();
                outputImageB.setRGB(x, y, iPixel);
                
                iColor = new Color(gray, gray, gray);
                iPixel = iColor.getRGB();
                outputImage.setRGB(x, y, iPixel);
                
                iColor = new Color(iBlue, iRed, iGreen);
                iPixel = iColor.getRGB();
                outputImageF.setRGB(y, x, iPixel);
                
            }
        }
        ImageIO.write(outputImage, "PNG", new File("E:/Users/Joseph/Pictures/2980/NewPhoto.png") );
        ImageIO.write(outputImageR, "PNG", new File("E:/Users/Joseph/Pictures/2980/NewPhotoR.png") );
        ImageIO.write(outputImageG, "PNG", new File("E:/Users/Joseph/Pictures/2980/NewPhotoG.png") );
        ImageIO.write(outputImageB, "PNG", new File("E:/Users/Joseph/Pictures/2980/NewPhotoB.png") );
        ImageIO.write(outputImageF, "PNG", new File("E:/Users/Joseph/Pictures/2980/NewPhotoF.png") );
    }
    
}