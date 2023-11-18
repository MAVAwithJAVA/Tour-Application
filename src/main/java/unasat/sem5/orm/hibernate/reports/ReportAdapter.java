package unasat.sem5.orm.hibernate.reports;

public class ReportAdapter implements Report {

    File file;

    public ReportAdapter(File file) {
        this.file = file;
    }

    @Override
    public void displayReport() {
        file.readReport();
    }

}
