package TEST;

import javax.sound.sampled.*;
import java.io.File;

public class Test_Audio_one {


    private static SourceDataLine line;
    private static int k;

    public static void main(String[] args) throws Exception {
        Mixer.Info[] info = AudioSystem.getMixerInfo();
        for (;k<info.length;k++) {
            System.out.println(info[k]);
        }
        AudioSystem.getMixer(info[4]);

        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("e:\\idea\\music\\一颗狼星-无人区玫瑰.wav"));
            AudioFormat format = inputStream.getFormat();
            DataLine.Info info1 = new DataLine.Info(SourceDataLine.class,format);
        }catch (Exception e){
            System.out.println("获取行失败");
            System.exit(0);
        }





    }
}
