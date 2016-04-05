package com.test.fitnesse;

import fitnesse.ComponentFactory;
import fitnesse.FitNesse;
import fitnesse.FitNesseContext;
import fitnesse.authentication.PromiscuousAuthenticator;
import fitnesse.html.HtmlPageFactory;
import fitnesse.responders.ResponderFactory;
import fitnesse.wiki.FileSystemPage;

/**
 * Created by renuka.prasad on 4/5/2016.
 */
public class StartFitnesseServer {

    public static void main(String[] args) throws Exception {
        StartFitnesseServer startFitnesseServer = new StartFitnesseServer();
        startFitnesseServer.start();
    }

    public void start() throws Exception {
        FitNesseContext context = loadContext();
        FitNesse fitNesse = new FitNesse(context);
        fitNesse.applyUpdates();
        boolean started = fitNesse.start();
        if (started) {
            printStartMessage(context);
        }
    }

    private void printStartMessage(FitNesseContext context) {
        System.out.println(context.toString());
    }

    protected FitNesseContext loadContext() throws Exception {
        FitNesseContext context = new FitNesseContext();
        ComponentFactory componentFactory = new ComponentFactory(context.rootPath);
        context.port = 8000;
        context.rootPath = ".";
        context.rootPageName = "FitNesseRoot";
        context.rootPagePath = context.rootPath + "/" + context.rootPageName;
        context.root = componentFactory.getRootPage(FileSystemPage.makeRoot(context.rootPath, context.rootPagePath));
        context.responderFactory = new ResponderFactory(context.rootPagePath);
        context.logger = null;
        context.authenticator = componentFactory.getAuthenticator(new PromiscuousAuthenticator());
        context.htmlPageFactory = componentFactory.getHtmlPageFactory(new HtmlPageFactory());

        context.responderFactory.addResponder("test", InheritClasspathTestResponser.class);
        context.responderFactory.addResponder("debug", DebugClasspathTestResponder.class);

        String extraOutput = componentFactory.loadResponderPlugins(context.responderFactory);
        extraOutput += componentFactory.loadWikiWidgetPlugins();
        extraOutput += componentFactory.loadContentFilter();
        return context;
    }
}
