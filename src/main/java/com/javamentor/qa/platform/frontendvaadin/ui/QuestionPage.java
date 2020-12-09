package com.javamentor.qa.platform.frontendvaadin.ui;

import com.javamentor.qa.platform.frontendvaadin.service.QuestionServiceVaadin;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicReference;

@PageTitle("Question Page")
@Route(value = "question", layout = MainLayout.class)
@CssImport("./styles/question-page.css")
public class QuestionPage extends Main implements HasUrlParameter<Integer> {

    @Autowired
    transient QuestionServiceVaadin service;

    //  Если не использовать передаваемых параметров, можно положить вью в конструктор, например public QuestionPage(@Autowired QuestionServiceVaadin service){}
    // иначе надо класть view в метод setParameter() для передачи параметров из пути во view
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        int questionId;
        if (parameter == null) {
            questionId = 155;
        } else {
            questionId = parameter;
        }
        final Text qTitle = new Text(service.getQuestionsAsync(questionId).getTitle());
        final Text qDescription = new Text(service.getQuestionsAsync(questionId).getDescription());

        Span infoString = new Span("Asked today, Active today, Viewed 1 time(s)");
        infoString.addClassName("info-string");
        HorizontalLayout window = new HorizontalLayout();
        window.addClassName("window-q");

        // left bar with menu links
//        RouterLink homeLink = new RouterLink("Home", NoVaadinView.class);
//        RouterLink tagLink = new RouterLink("Tags", NoVaadinView.class);
//        RouterLink userLink = new RouterLink("Users", NoVaadinView.class);
//        homeLink.setHighlightCondition(HighlightConditions.sameLocation());
//        VerticalLayout leftBar = new VerticalLayout(homeLink, new Text("PUBLIC"), tagLink, userLink);
//        leftBar.addClassName("leftbar");
//
//        window.add(leftBar);

        //Question title panel
        VerticalLayout headMainColon = new VerticalLayout();
        headMainColon.addClassName("head-main-colon");
        headMainColon.add(qTitle);


        //Voting panel
        AtomicReference<Integer> voteCount = new AtomicReference<>(0);
        Icon upIcon = new Icon(VaadinIcon.CARET_UP);
        upIcon.addClickListener(evt -> upIcon.setColor("blue"));
        Icon downIcon = new Icon(VaadinIcon.CARET_DOWN);
        Icon isAcceptedAnwser = new Icon(VaadinIcon.CHECK);
        downIcon.addClickListener(evt -> downIcon.setColor("blue"));
        Text numberOfVotes = new Text(voteCount.get().toString());
        VerticalLayout voteColon = new VerticalLayout(upIcon, numberOfVotes, downIcon, isAcceptedAnwser);
        voteColon.addClassName("vote-colon");
        voteColon.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        // main question panel
        VerticalLayout mainColon = new VerticalLayout();
        mainColon.addClassName("main-colon");
        mainColon.add(qDescription);

        RouterLink addComment = new RouterLink("add a comment", NoVaadinView.class);
        RouterLink authorLink = new RouterLink("Author", NoVaadinView.class);
        authorLink.getHighlightAction();
        authorLink.setId("author-widget");
        //Answer panel
        VerticalLayout answerPanel = new VerticalLayout(new Text("Your Answer"));
        answerPanel.addClassName("answer-panel");

        mainColon.add(addComment, authorLink, answerPanel);

        //blog panel
        VerticalLayout blogColon = new VerticalLayout(new Text("Blog"));
        blogColon.addClassName("blog-colon");

        HorizontalLayout threeWindows = new HorizontalLayout(voteColon,mainColon,blogColon);
        headMainColon.add(infoString, threeWindows);
        window.add(headMainColon);

        //  'Ask Question' button
        Button askQuestion = new Button("Ask question", evt -> new RouterLink("noview", NoVaadinView.class));
        askQuestion.addClassName("ask-question");
        window.add(askQuestion);
        add(window);
    }
}
