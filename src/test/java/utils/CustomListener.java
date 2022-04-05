package test.java.utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ " -- Test method failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ " -- Test method skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ " -- Test method success\n");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }
}
