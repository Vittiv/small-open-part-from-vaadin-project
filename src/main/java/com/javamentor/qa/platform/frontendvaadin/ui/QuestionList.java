package com.javamentor.qa.platform.frontendvaadin.ui;

import com.javamentor.qa.platform.frontendvaadin.dto.QuestionDto;
import com.javamentor.qa.platform.frontendvaadin.entity.question.Question;
import com.javamentor.qa.platform.frontendvaadin.service.QuestionServiceVaadin;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.List;

@PageTitle("Question List")
@Route(value = "questionarea", layout = MainLayout.class)
@CssImport("./styles/question-page.css")
public class QuestionList extends VerticalLayout {



    private Grid<Question> questionList = new Grid<>(Question.class);

    public QuestionList(@Autowired QuestionServiceVaadin service) {

        int questionId = 155;

        final Text qTitle = new Text(service.getQuestionsAsync(questionId).getTitle());
        final Text qDescription = new Text(service.getQuestionsAsync(questionId).getDescription());

        HorizontalLayout window = new HorizontalLayout();
        window.addClassName("window-q");

        RouterLink homeLink = new RouterLink("Главная", NoVaadinView.class);
        RouterLink questionLink = new RouterLink("Вопросы", NoVaadinView.class);
        RouterLink tagLink = new RouterLink("Метки", NoVaadinView.class);
        RouterLink userLink = new RouterLink("Участники", NoVaadinView.class);
        RouterLink withoutAnswerLink = new RouterLink("Неотвеченные", NoVaadinView.class);
        questionLink.setHighlightCondition(HighlightConditions.sameLocation());
        VerticalLayout leftBar = new VerticalLayout(homeLink, tagLink, userLink, withoutAnswerLink);
        leftBar.addClassName("leftbar");
        window.add(leftBar);

        VerticalLayout mainBar = new VerticalLayout();
        mainBar.addClassName("main-bar");
        //Question title panel
        Span allQuestions = new Span("Все вопросы");
        Span oneHundredQuestions = new Span("100 вопросов 100 вопросов 100 вопросов");
        oneHundredQuestions.addClassName("one-hundred-questions");
        allQuestions.addClassName("all-question-title");
        //  'Ask Question' button
        Button askQuestion = new Button("Задать вопрос", evt -> new RouterLink("noview", NoVaadinView.class));
        askQuestion.addClassName("ask-question");
        HorizontalLayout titleHead = new HorizontalLayout();
        titleHead.addClassName("title-head");
        titleHead.add(allQuestions, askQuestion);
        VerticalLayout headMainColon = new VerticalLayout();
        headMainColon.addClassName("head-main-column");
        Span tabsPanel = new Span("Новые Текущие Неотвеченные Конкурсные Еще");
        headMainColon.add(titleHead, oneHundredQuestions, tabsPanel);

        RouterLink filterWeek = new RouterLink("За неделю", NoVaadinView.class);
        RouterLink filterPopular = new RouterLink("По популярности", NoVaadinView.class);
        HorizontalLayout filters = new HorizontalLayout(filterWeek, filterPopular);
        filters.addClassName("filters");
        headMainColon.add(filters);
        mainBar.add(headMainColon);

        Accordion accordion = new Accordion();

        VerticalLayout personalInformationLayout = new VerticalLayout();
        personalInformationLayout.add(
                new Text("Name"),
                new Text("Phone"),
                new Text("Email")
        );
        accordion.add("Personal Information", personalInformationLayout);
//        QuestionDto resultList = service.getQuestionList(1,10);
        VerticalLayout billingAddressLayout = new VerticalLayout();
//        billingAddressLayout.add(
//                new Text(resultList.getAuthorName()),
//                new Text(resultList.getTitle()),
//                new Text(resultList.getDescription()),
//                new Text(resultList.getPersistDateTime().format(DateTimeFormatter.BASIC_ISO_DATE))
//        );
        accordion.add("Billing Address", billingAddressLayout);

        VerticalLayout paymentLayout = new VerticalLayout();
        paymentLayout.add(
                new Span("Not yet implemented")
        );
        AccordionPanel billingAddressPanel = accordion.add("Payment", paymentLayout);
        billingAddressPanel.setEnabled(false);
        mainBar.add(accordion);
//        VerticalLayout list = new VerticalLayout();
//        list.addClassName("list-view");
//        configureGrid();
//        list.add(questionList);
//        mainBar.add(list);
//        questionList.setItems((Collection<Question>) );
        window.add(mainBar);
        add(window);

    }

    private void configureGrid() {
        questionList.addClassName("question-list");
        questionList.setSizeFull();
        questionList.setColumns("title","description");
    }


}
