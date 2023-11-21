package unasat.sem5.orm.hibernate.reports;


public class ReportAdapter implements Report {
    // 2 soorten adapters nl: Calls adapter en Object Adapter

    File file;

    public ReportAdapter(File file) {
        this.file = file;
    }

    @Override
    public void displayRawReport() {
        file.readReport();
    }

}
