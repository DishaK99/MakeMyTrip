package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static void captureScreen( WebDriver driver ) throws IOException {

            TakesScreenshot ts = (TakesScreenshot)driver;

            File file = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./Screenshots/"+timeStamp()+".png"));
    }

    public static String timeStamp()
    {
        return new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
    }

}
