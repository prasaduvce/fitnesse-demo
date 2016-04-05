package com.test.fitnesse;

import fitnesse.responders.run.TestResponder;
import fitnesse.wiki.PageData;

/**
 * Created by renuka.prasad on 4/5/2016.
 */
public class DebugClasspathTestResponder extends TestResponder {

    private static final String DEBUG_PORT = "12000";

    @Override
    protected String buildCommand(PageData data, String program, String classpath) throws Exception {
        String parentClassPath = System.getProperty("java.class.path");
        String pathSeprator = System.getProperty("path.separator");
        String[] classPathElements = parentClassPath.split(pathSeprator);
        for (String element: classPathElements) {
            classpath += pathSeprator + "\"" + element + "\"";
        }
        String debugOptions = "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=" + DEBUG_PORT;
        return "java "+debugOptions + " "+"-cp "+classpath + " " + program;
    }
}
