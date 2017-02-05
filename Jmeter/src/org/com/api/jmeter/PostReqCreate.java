package org.com.api.jmeter;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class PostReqCreate {
	protected void Runner() throws MalformedURLException
	{
		File jmeterHome = new File("jmeterHome");
		if (jmeterHome.exists()) {
			File jmeterProperties = new File(jmeterHome.getPath() + "\\bin\\jmeter-post.properties");
			if (jmeterProperties.exists()) {
				//JMeter Engine
				StandardJMeterEngine jmeter = new StandardJMeterEngine();
				//JMeter initialisation (properties, log levels, locale, etc)
				JMeterUtils.setJMeterHome(jmeterHome.getPath());
				JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
				JMeterUtils.initLogging();// you can comment this line out to see extra log messages
				JMeterUtils.initLocale();

				testPlan();

				HashTree testPlanTree =  testPlan();
				jmeter.configure(testPlanTree);

				//saving summary of response into a csv
				saveResponse(testPlanTree);

				//Run jmeter test plan
				jmeter.run();
			}
		}
	}

	protected HashTree testPlan() throws MalformedURLException
	{
		Random rn = new Random();
		HashTree testPlanTree = new HashTree();

		for(int count=0; count<3;count++)
		{
			// First HTTP Sampler - open url
			HTTPSampler examplecomSampler = new HTTPSampler();
			examplecomSampler.setProtocol("http");
			examplecomSampler.setDomain("qa5.smallbizvoices.com");
			examplecomSampler.setPath("/alps/manage/allstate/projects?format=json");
			examplecomSampler.setMethod("POST");
			examplecomSampler.setName("Create-Test");
			examplecomSampler.setDoMultipartPost(true);
			int random_number = rn.nextInt((99999-11111)+1) + 11111;
			String proj_name = "Proj-" + String.valueOf(random_number);
			examplecomSampler.addArgument("tenant_code", "allstate");
			examplecomSampler.addArgument("locale", "en-us");
			examplecomSampler.addArgument("crawling_frequency", "monthly");
			examplecomSampler.addArgument("domain_url", "http://www.postreq-JmeterTest.com");
			examplecomSampler.addArgument("name", proj_name);
			String url = examplecomSampler.getUrl().toString();
			System.out.println(url);
			examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
			examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

			// Loop Controller
			LoopController loopController = new LoopController();
			loopController.setLoops(1);
			loopController.setFirst(true);
			loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
			loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
			loopController.initialize();

			// Thread Group
			ThreadGroup threadGroup = new ThreadGroup();
			threadGroup.setName("Sample Thread Group");
			threadGroup.setNumThreads(1);
			threadGroup.setRampUp(1);
			threadGroup.setSamplerController(loopController);
			threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
			threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());

			// Test Plan
			TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
			testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
			testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
			testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

			testPlanTree.add(testPlan);
			HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
			threadGroupHashTree.add(examplecomSampler);
			System.out.println(testPlan);
		}
		return testPlanTree;
	}
	protected void saveResponse(HashTree testPlanTree){
		//add Summarizer output to get test progress in stdout like:
		// summary =      2 in   1.3s =    1.5/s Avg:   631 Min:   290 Max:   973 Err:     0 (0.00%)
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		//Store execution results into a csv file
		String report = "create_report.xml";
		ResultCollector logger = new ResultCollector(summer);
		ResultCollector csvlogger = new ResultCollector(summer);
		csvlogger.setFilename(report);
		testPlanTree.add(testPlanTree.getArray()[0], logger);
		testPlanTree.add(testPlanTree.getArray()[0], csvlogger);
	}
}


