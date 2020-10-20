package com.test_framework;
import  org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


/**
 * @program: testAppium
 * @description: 主要方法脱离代码执行
 * @author: mengmeng
 * @create: 2020-10-16 14:54
 **/
public class Main {
    public static void main(String []args){
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage("test_framework"),
                        selectClass(DDTest.class)
                )
                .filters(
                        includeClassNamePatterns(".*")
                )
                .build();

        Launcher launcher = LauncherFactory.create();

// Register a listener of your choice
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        System.out.println(summary.getContainersAbortedCount());
// Do something with the TestExecutionSummary.
    }
}
