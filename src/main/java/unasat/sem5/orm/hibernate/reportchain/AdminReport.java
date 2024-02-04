package unasat.sem5.orm.hibernate.reportchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminReport {
    // TODO Een lijst aantal reports komen hierin te staan

    private static final List<String> reportList = new ArrayList<>(Arrays.asList("Traveler report", "Destination report", "Period Report"));

    public List<String> getReportList(){
        return reportList;
    }
}
