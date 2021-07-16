package com.example.application.views.helloworld;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.shared.Registration;

@StyleSheet(value = "https://unpkg.com/bpmn-js@8.4.0/dist/assets/diagram-js.css")
@StyleSheet(value = "https://unpkg.com/bpmn-js@8.4.0/dist/assets/bpmn-font/css/bpmn.css")
@JsModule(value = "./bpmn/bpmn-modeler.development.js")
@JsModule(value = "./bpmn/wrapper.js")
@Tag(value = "div")
public class BpmnEditor extends Component implements HasSize {

	private static final long serialVersionUID = 1L;

	public BpmnEditor() {
		setId("bpmn-editor");
	}

	public void setValue(String value) {
//		Notification.show(value, 3000, Position.TOP_CENTER);
		UI.getCurrent().getPage().executeJs("initEditor($0)", value);
	}

	@DomEvent("custom-click")
	public static class ClickEvent extends ComponentEvent<BpmnEditor> {

		private static final long serialVersionUID = 1L;
		private final String elementId;
		private final String elementType;

		public ClickEvent(BpmnEditor source, boolean fromClient, @EventData("event.detail.elementId") String elementId,
				@EventData("event.detail.elementType") String elementType) {
			super(source, fromClient);
			this.elementId = elementId;
			this.elementType = elementType;
		}

		public String getElementId() {
			return elementId;
		}

		public String getElementType() {
			return elementType;
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
}
