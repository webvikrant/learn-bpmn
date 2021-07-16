import BpmnJS from "./bpmn-modeler.development.js";

var bpmnModeler;

async  function openDiagram(bpmnXML) {

	// import diagram

	try {
		await bpmnModeler.importXML(bpmnXML);
		// access modeler components
		var canvas = bpmnModeler.get('canvas');
		var overlays = bpmnModeler.get('overlays');

		// zoom to fit full viewport
		canvas.zoom('fit-viewport');
		// attach an overlay to a node
		overlays.add('SCAN_OK', 'note', {
			position: {
				bottom: 0,
				right: 0
			},
			html: '<div class="diagram-note">Mixed up the labels?</div>'
		});
		// add marker
		canvas.addMarker('SCAN_OK', 'needs-discussion');
	} catch (err) {
		console.log(err);
	}
}

window.initEditor = function(value){


	try{
		
		document.getElementById("bpmn-editor").innerHTML = "";
		bpmnModeler = new BpmnJS({
			container: '#bpmn-editor',
			width: '90%',
      		height: '500px',
			keyboard: {
				bindTo: window
			}
		});


		if(null === value){
		
			openDiagram('<?xml version="1.0" encoding="UTF-8"?><bpmn:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" id="Definitions_1176k0r" targetNamespace="http://bpmn.io/schema/bpmn" exporter="bpmn-js (https://demo.bpmn.io)" exporterVersion="8.5.0"><bpmn:process id="Process_1cgwnaz" /><bpmndi:BPMNDiagram id="BPMNDiagram_1"><bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1cgwnaz" /></bpmndi:BPMNDiagram></bpmn:definitions>');
		
		}else{
			
			openDiagram(value);

		}
		
		var eventBus = bpmnModeler.get("eventBus");

		eventBus.on("element.click", function(event) {
			var element = event.element;
			console.clear();
			console.log(event);
			console.log('Element id: '+element.id);
			console.log('Element type: '+ element.type);
			
			var customClick = new CustomEvent('custom-click', { detail: {elementId:element.id, elementType:element.type}});
			
			var editorElement = document.getElementById("bpmn-editor");
			editorElement.dispatchEvent(customClick);
			
		});
		
	}catch(e){
		console.error('error in load bpmn module', e);
	}
}