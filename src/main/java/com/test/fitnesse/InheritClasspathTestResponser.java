package com.test.fitnesse;

import fitnesse.responders.run.TestResponder;
import fitnesse.wiki.PageData;

/**
 * Created by renuka.prasad on 4/5/2016.
 */
public class InheritClasspathTestResponser extends TestResponder {

    @Override
    protected String buildCommand(PageData data, String program, String classpath) throws Exception {
        String parentClassPath = System.getProperty("java.class.path");
        String pathSeprator = System.getProperty("path.separator");
        String[] classPathElements = parentClassPath.split(pathSeprator);
        for (String element: classPathElements) {
            classpath += pathSeprator + "\"" + element + "\"";
        }
        return super.buildCommand(data, program, classpath);
    }
}
