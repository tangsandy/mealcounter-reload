package at.htl.nfc;

import at.htl.nfc.TagLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagLoaderTest {



    @Test
    public void testSendData(){

        TagLoader tagLoader = new TagLoader();
        tagLoader.sendData("http://152.70.175.53/api/nfcCard/assignCard/7070/5");

    }

    @Test
    public void getData(){
        TagLoader tagLoader = new TagLoader();

        try {
            tagLoader.getData("http://152.70.175.53/api/findByNfcId/7070");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
