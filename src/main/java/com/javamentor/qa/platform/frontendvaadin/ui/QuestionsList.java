package com.javamentor.qa.platform.frontendvaadin.ui;

import com.github.appreciated.card.ClickableCard;
import com.github.appreciated.card.content.Item;
import com.github.appreciated.card.label.PrimaryLabel;
import com.github.appreciated.card.label.SecondaryLabel;
import com.github.appreciated.card.label.TitleLabel;
import com.javamentor.qa.platform.frontendvaadin.dto.QuestionDto;
import com.javamentor.qa.platform.frontendvaadin.dto.TagDto;
import com.javamentor.qa.platform.frontendvaadin.service.QuestionServiceVaadin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.klaudeta.PaginatedGrid;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@PageTitle("Question List")
@Route(value = "questionlist", layout = MainLayout.class)
@CssImport("./styles/question-list.css")
public class QuestionsList extends VerticalLayout implements HasUrlParameter<String> {

    @Autowired
    transient QuestionServiceVaadin service;
//    private PaginatedGrid<QuestionDto> grid = new PaginatedGrid<>(QuestionDto.class);


    private Component getQuestionsCard(QuestionDto question) {
        VerticalLayout questionCards = new VerticalLayout();
        RouterLink questionPageLink = new RouterLink("", QuestionPage.class);
        questionCards.addClassName("card");
        //question card
        Item lastUpdateDate = new Item("", "" + question.getLastUpdateDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
        lastUpdateDate.addClassNames("last-update");
        TitleLabel questionTitle = new TitleLabel(question.getTitle());
        questionTitle.addClassName("question-title");
        PrimaryLabel questionDescription = new PrimaryLabel(question.getDescription());
        questionDescription.addClassName("question-description");
        SecondaryLabel tags = new SecondaryLabel(printTagsFromList(question.getListTagDto().subList(0, 5)
                .stream().map(TagDto::getName).collect(Collectors.toList())));
        tags.addClassName("tags");
        SecondaryLabel votesAndAnswers = new SecondaryLabel(" Голосов: " + question.getCountValuable() + " Ответов: " + question.getCountAnswer() + " Просмотров: " + question.getViewCount());
        votesAndAnswers.addClassName("votes-and-answers");
        SecondaryLabel askedDate = new SecondaryLabel( "задан " + question.getPersistDateTime().toLocalDate());
        askedDate.addClassNames("asked-date");
        Image author = new Image(question.getAuthorImage(), " Автор: " + question.getAuthorName());
        author.addClassName("author");
        ClickableCard qCard = new ClickableCard(
                onClick -> {/* Handle Card click */
                    UI.getCurrent().navigate("question");

                    },
                lastUpdateDate,
                questionTitle.withWhiteSpaceNoWrap(),
                questionDescription,
                tags,
                votesAndAnswers,
                askedDate,
                author
//                , new Actions(
//                        new ActionButton("Развернуть", event -> {/* Handle Action*/}),
//                        new ActionButton("Action 2", event -> {/* Handle Action*/}))
        );
        qCard.withWidth(questionCards.getWidth());
        questionCards.add(qCard);

        return questionCards;
    }

    private String printTagsFromList(List<String> list) {
        StringBuilder res = new StringBuilder();
        for (String elem : list) {
            res.append(elem + " ");
        }
       return res.toString();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameters) {
        VerticalLayout mainBar = new VerticalLayout();
        mainBar.addClassName("main-bar");
        //Question title panel
        Span pageTitle = new Span("Все вопросы");
        Span oneHundredQuestions = new Span("100 вопросов 100 вопросов 100 вопросов");
        oneHundredQuestions.addClassName("one-hundred-questions");
        pageTitle.addClassName("all-question-title");
        //  'Ask Question' button
        Button askQuestion = new Button("Задать вопрос", evt -> new RouterLink("noview", NoVaadinView.class));
        askQuestion.addClassName("ask-question");
        HorizontalLayout titleHead = new HorizontalLayout();
        titleHead.addClassName("title-head");
        titleHead.add(pageTitle, askQuestion);
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
        add(mainBar);
        // обработка номера и размера страницы
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        Integer size = Integer.getInteger(String.valueOf(parametersMap.get("size")));
        Integer page = Integer.getInteger(String.valueOf(parametersMap.get("page")));

        if (page == null || page < 1 || page > 100) page = 1;
        if (size == null || size < 1 || size > 100) size = 10;

        // question list
        UnorderedList list = new UnorderedList();
        list.addClassName("list-question");
        list.setWidthFull();
        // составляем список карточек с вопросами и пишем в компонент list
        service.getQuestionList(page, size).forEach(questionDto -> list.add(getQuestionsCard(questionDto)));
        add(list);
        //Pagination sample
    }
}
/*
private DataProvider<UserProfileView, Void> createDataProviderSpringDataAdapter(
						UserProfileViewService userProfileViewService ) {
				DataProvider<UserProfileView, Void> dataProvider = DataProvider.fromCallbacks(
								// First callback fetches items based on a query
								query -> {
										// The index of the first item to load
										int offset = query.getOffset();

										// The number of items to load
										int limit = query.getLimit();

										int page = offset / limit;

										List<UserProfileView> userProfileViews = new ArrayList<>(
														userProfileViewService.findAllUserProfileViews( page, limit ) );

										return userProfileViews.stream();
								},
								// Second callback fetches the number of items
								// for a query

								query -> userProfileViewService.countUserProfileViews() );

				return dataProvider;
		}
 */
