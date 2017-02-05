package org.com.api.jmeter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
import org.json.JSONArray;
import org.json.JSONObject;

public class PutrequestEdit {
	protected void Runner() throws IOException
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

	protected HashTree testPlan() throws IOException
	{
		HashTree testPlanTree = new HashTree();
		ResponseParser res = new ResponseParser();
		for(int count=0; count<12;count++)
		{
			HTTPSampler examplecomSampler = new HTTPSampler();
			JSONArray resp =  res.response();
			JSONObject jobj = resp.getJSONObject(count);
			int id = res.singleObject(jobj);
			res.cleanObject(jobj);
			jobj.put("id", String.valueOf(id));
			Iterator<String> keys = jobj.keys();
			while(keys.hasNext())
			{
				String key = (String)keys.next(); 
				String value = jobj.getString(key);
				examplecomSampler.addArgument(key, value);
			}
			//			System.out.println(key);
			//			System.out.println(jobj);

			// First HTTP Sampler - open url

			examplecomSampler.setProtocol("http");
			examplecomSampler.setDomain("qa5.smallbizvoices.com");
			examplecomSampler.setPath("/alps/manage/allstate/projects/"+id+"?format=json");
			examplecomSampler.setMethod("POST");
			examplecomSampler.setName("Edit-Test");
			examplecomSampler.parseArguments("mutipart/form-data");
			//			examplecomSampler.setPostBodyRaw(true);
			//			String url = examplecomSampler.getUrl().toString();
			//			System.out.println(url);
			System.out.println(examplecomSampler.getQueryString());
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
		String report = "edit_report.xml";
		ResultCollector logger = new ResultCollector(summer);
		ResultCollector csvlogger = new ResultCollector(summer);
		csvlogger.setFilename(report);
		testPlanTree.add(testPlanTree.getArray()[0], logger);
		testPlanTree.add(testPlanTree.getArray()[0], csvlogger);
	}

}
