package com.javamentor.qa.platform.frontendvaadin.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "noview", layout = MainLayout.class)
public class NoVaadinView extends VerticalLayout {
    public NoVaadinView() {
        HorizontalLayout layout = new HorizontalLayout(new Text("No view!"));
        layout.setAlignItems(Alignment.CENTER);
        add(layout);
    }
}
