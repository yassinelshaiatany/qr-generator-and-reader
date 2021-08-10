package qrgenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
        
public class Tools {
    
    public static void infoMsgBox(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static void confirmMsgBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void errMsgBox(String message , String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void CreateFolder(String folderName, String path){
        File f = new File(path + "/" + folderName);
        f.mkdir();
    }
    
    public static void CreateFolder(String folderName){
        File f = new File(folderName);
        f.mkdir();
    }
    
    public static void openForm(JFrame form){
        try{
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("logo.png"));
            form.setIconImage(img);
            form.setDefaultCloseOperation(2);
            form.setVisible(true); 
        }catch (IOException ex){
            infoMsgBox(ex.getMessage());
        }     
    }
    
    public static void generateQR(JTextField txtUrl, JTextField txtPath, JTextField txtFileName) throws Exception {
        String data = txtUrl.getText();
        String path = txtPath.getText()+"\\"+txtFileName.getText();
        
        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(path));
    }
    
    public static void readQR(JTextField txtPath) throws Exception {
        String path = txtPath.getText();
        
        BufferedImage bf = ImageIO.read(new FileInputStream(path));
        
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(bf)));
        
        Result result = new MultiFormatReader().decode(bitmap);
        
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(result.getText()));
    }
}