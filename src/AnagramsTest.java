import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnagramsTest {

    @Test
    void test1() {
        //Default test
        Anagrams a = new Anagrams();
        try {
            a.processFile("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> aMaxEntries = a.getMaxEntries();
        ArrayList<ArrayList<String>> aListOfAnagrams = new ArrayList<>();
        ArrayList<Long> aListOfKeys = new ArrayList<>();
        int aLengthOfMaxAnagrams = 0;
        for(Map.Entry<Long, ArrayList<String>> entry : aMaxEntries){
            aListOfAnagrams.add(entry.getValue());
            aListOfKeys.add(entry.getKey());
            aLengthOfMaxAnagrams = entry.getValue().size();
        }
        assertEquals( "[236204078]", aListOfKeys.toString());
        assertEquals("[[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]", aListOfAnagrams.toString());
        assertEquals( 15, aLengthOfMaxAnagrams);

        //Tests for multiple anagrams (Should return size 14)
        Anagrams b = new Anagrams();
        try {
            b.processFile("words_alpha_no15.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> bMaxEntries = b.getMaxEntries();
        ArrayList<ArrayList<String>> bListOfAnagrams = new ArrayList<>();
        ArrayList<Long> bListOfKeys = new ArrayList<>();
        int bLengthOfMaxAnagrams = 0;
        for(Map.Entry<Long, ArrayList<String>> entry : bMaxEntries){
            bListOfAnagrams.add(entry.getValue());
            bListOfKeys.add(entry.getKey());
            bLengthOfMaxAnagrams = entry.getValue().size();
        }
        assertEquals( "[4765442, 6383894, 6313671166]", bListOfKeys.toString());
        assertEquals("[[apers, apres, asper, pares, parse, pears, prase, presa, rapes, reaps, repas, spaer, spare, spear], [arest, aster, astre, rates, reast, resat, serta, stare, strae, tares, tarse, tears, teras, treas], [anestri, antsier, asterin, eranist, nastier, ratines, resiant, restain, retains, retinas, retsina, stainer, starnie, stearin]]", bListOfAnagrams.toString());
        assertEquals( 14, bLengthOfMaxAnagrams);

        //Try empty .txt
        Anagrams c = new Anagrams();
        try {
            c.processFile("empty.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> cMaxEntries = c.getMaxEntries();
        ArrayList<ArrayList<String>> cListOfAnagrams = new ArrayList<>();
        ArrayList<Long> cListOfKeys = new ArrayList<>();
        int cLengthOfMaxAnagrams = 0;
        for(Map.Entry<Long, ArrayList<String>> entry : cMaxEntries){
            cListOfAnagrams.add(entry.getValue());
            cListOfKeys.add(entry.getKey());
            cLengthOfMaxAnagrams = entry.getValue().size();
        }
        assertEquals( "[]", cListOfKeys.toString());
        assertEquals("[]", cListOfAnagrams.toString());
        assertEquals( 0, cLengthOfMaxAnagrams);
    }
}