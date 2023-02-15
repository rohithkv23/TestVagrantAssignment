import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class RCBTeamValidation {
    File rcbTeamFile;

    @BeforeTest
    public void readTeamRCBFile() {
        String rcbTeamFilePath = System.getProperty("user.dir") + File.separator + "data" + File.separator
                + "TeamRCB.json";
        try {
            rcbTeamFile = new File(rcbTeamFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void validateForeignPlayersCount() {

        String jsonPath = "$.player[?(@.country!='India')]";
        try {
            List<Object> foreignPlayers = JsonPath.parse(rcbTeamFile).read(jsonPath);
            Assert.assertTrue(foreignPlayers.size() <= 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateWicketKeepersCount() {
        String jsonPath = "$.player[?(@.role=='Wicket-keeper')]";
        try {
            List<Object> wicketKeepers = JsonPath.parse(rcbTeamFile).read(jsonPath);
            Assert.assertTrue(wicketKeepers.size() >= 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}