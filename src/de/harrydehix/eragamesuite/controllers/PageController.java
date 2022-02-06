package de.harrydehix.eragamesuite.controllers;

import de.harrydehix.eragamesuite.Application;

import java.awt.*;

public class PageController {
    private Application application;

    public void setApplication(Application application) {
        this.application = application;
    }

    public void switchTo(Component component) {
        application.switchTo(component);
    }
}
