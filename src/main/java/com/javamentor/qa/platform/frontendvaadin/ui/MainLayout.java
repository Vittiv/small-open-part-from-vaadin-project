package com.javamentor.qa.platform.frontendvaadin.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;


@CssImport("./styles/css-file.css")
public class MainLayout extends AppLayout {
    private final H3 viewTitle = new H3("LOGO");
    public MainLayout() {

        // header
        addToNavbar(createHeaderContent());
        // панель меню слева
        addToDrawer(createLeftBarContent());
    }

    private Component createLeftBarContent() {
        VerticalLayout menu = new VerticalLayout();
        menu.addClassName("left=menu-bar");
        RouterLink homeLink = new RouterLink("Главная", NoVaadinView.class);
        RouterLink questionLink = new RouterLink("Вопросы", NoVaadinView.class);
        RouterLink tagLink = new RouterLink("Метки", NoVaadinView.class);
        RouterLink userLink = new RouterLink("Участники", NoVaadinView.class);
        RouterLink withoutAnswerLink = new RouterLink("Неотвеченные", NoVaadinView.class);
        questionLink.setHighlightCondition(HighlightConditions.sameLocation());
        menu.add(homeLink, questionLink, tagLink, userLink, withoutAnswerLink);
        return menu;
    }
    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        viewTitle.setId("logo");
        layout.add(viewTitle);
        layout.getThemeList().set("dark", true);
        Icon logoH = new Icon(VaadinIcon.VAADIN_H);
        Icon bellIcon = new Icon(VaadinIcon.BELL);
        Icon trophyIcon = new Icon(VaadinIcon.TROPHY);
        TextField searchField = new TextField();
        searchField.setId("search-field");
        Image avatar = new Image("images/user.svg", "Avatar");
        avatar.addClickListener(evt -> new RouterLink("profile", QuestionPage.class));
        layout.add(searchField, logoH, bellIcon, trophyIcon, avatar);
        logoH.getStyle().set("cursor", "pointer");
        bellIcon.getStyle().set("cursor", "pointer");
        trophyIcon.getStyle().set("cursor", "pointer");
        viewTitle.getStyle().set("cursor", "pointer");
        avatar.getStyle().set("cursor", "pointer");
        return layout;
    }
}
