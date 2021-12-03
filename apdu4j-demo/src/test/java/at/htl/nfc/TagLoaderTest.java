package at.htl.nfc;

import at.htl.nfc.TagLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagLoaderTest {



    @Test
    public void testSendData(){

        TagLoader tagLoader = new TagLoader();
        tagLoader.sendData("http://152.70.175.53/api/nfccard/assign-card/3424/5");

    }

    @Test
    public void getData(){
        TagLoader tagLoader = new TagLoader();

        try {
            tagLoader.getData("http://152.70.175.53/api/nfccard/nfcid/1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
