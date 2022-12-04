
package com.mycompany.mavenproject1;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Carlton Davis
 * Adapted code from The Pi4J Project:
 * https://pi4j.com
 * 
 */
public class CameraApp implements IApplicationCamera{
    
    //Pi4J code to control camera

    /**
     *
     * @param pi4j
     */
   
    @Override
    public void execute(Context pi4j) {
        System.out.println("\nInitializing the camera");
        Camera camera = new Camera();

        System.out.println("Setting up the config to take a picture.");
        
        //Configure the camera setup
        var config = Camera.PicConfig.Builder.newInstance()
                .outputPath("src/main/resources/media/Pics/")
		.delay(3000)
		.disablePreview(true)
		.encoding(Camera.PicEncoding.PNG)
		.useDate(true)
		.quality(93)
		.width(1280)
		.height(800)
		.build();

        //Take the picture
        camera.takeStill(config);

        System.out.println("waiting for camera to take pic");
        delay(4000);

        System.out.println("Taking a video for 3 seconds");
        
        //Configure the video setup
        var vidconfig = Camera.VidConfig.Builder.newInstance()
                .outputPath("/home/raagav/Videos/")
		.recordTime(3000)
		.useDate(true)
		.build();
        
        //Take the video
        camera.takeVid(vidconfig); 
    }

    
    public static void main(String[] args) {

        //Initialize the Pi4J Runtime Context
        var pi4j = Pi4J.newAutoContext();

        CameraApp runApp = new CameraApp();
        runApp.execute(pi4j);
        
        // Shutdown Pi4J
        pi4j.shutdown();

    }
    
    public void callCamera() {
        //Initialize the Pi4J Runtime Context
        var pi4j = Pi4J.newAutoContext();

        CameraApp runApp = new CameraApp();
        runApp.execute(pi4j);
        
        // Shutdown Pi4J
        pi4j.shutdown();
    }
    
    private File getRecentImage() {
        File dir = new File("src/main/resources/media/Pics/");
        if(dir.isDirectory()) {
            Optional<File> opFile = Arrays.stream(dir.listFiles(File::isFile))
                    .max((f1, f2) -> Long.compare(f1.lastModified() , f2.lastModified()));
            
            if(opFile.isPresent()) {
                return opFile.get();
            }
        }
        return null;
    }
    
    public String getRecentImageBytes() throws FileNotFoundException, IOException {
//        File file = getRecentImage();
//        FileInputStream fis = new FileInputStream(file);
//        
//        byte[] arr = new byte[(int)file.length()];
//        fis.read(arr);
//        fis.close();
//        
//        String s = Base64.getEncoder().encodeToString(arr);

        byte[] fileContent = FileUtils.readFileToByteArray(getRecentImage());
        
        String s = Base64.getEncoder().encodeToString(fileContent);
        
        return s;
    }
}
