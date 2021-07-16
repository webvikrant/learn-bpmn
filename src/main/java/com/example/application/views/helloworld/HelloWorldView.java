package com.example.application.views.helloworld;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.example.application.views.helloworld.BpmnEditor.ClickEvent;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	public HelloWorldView() {

		BpmnEditor bpmnEditor = new BpmnEditor();
		bpmnEditor.setWidthFull();
		bpmnEditor.setHeight("500px");

		add(bpmnEditor);

		bpmnEditor.setValue(null);

		bpmnEditor.addListener(ClickEvent.class, this::handleClickEvent);
	}

	private void handleClickEvent(ClickEvent event) {
		Notification.show("Element Id: " + event.getElementId() + ", Type: " + event.getElementType(), 3000,
				Position.TOP_CENTER);
	}
}
