private void handleFireEvent(ArgoEvent event, ArgoEventListener listener) {
        if (event.getEventType() == ArgoEventTypes.ANY_EVENT) {
            if (listener instanceof ArgoModuleEventListener) {
                handleFireModuleEvent((ArgoModuleEvent) event,
				      (ArgoModuleEventListener) listener);
            }
            if (listener instanceof ArgoNotationEventListener) {
                handleFireNotationEvent((ArgoNotationEvent) event,
					(ArgoNotationEventListener) listener);
            }
        } else {
            if (event.getEventType() >= ArgoEventTypes.ANY_MODULE_EVENT
                && event.getEventType() < ArgoEventTypes.LAST_MODULE_EVENT) {
                if (listener instanceof ArgoModuleEventListener) {
                    handleFireModuleEvent((ArgoModuleEvent) event,
					  (ArgoModuleEventListener) listener);
                }
            }
            if (event.getEventType() >= ArgoEventTypes.ANY_NOTATION_EVENT
                && event.getEventType() < ArgoEventTypes.LAST_NOTATION_EVENT) {
                if (listener instanceof ArgoNotationEventListener) {
                    handleFireNotationEvent((ArgoNotationEvent) event,
					(ArgoNotationEventListener) listener);
                }
            }
            if (event.getEventType() >= ArgoEventTypes.ANY_GENERATOR_EVENT
                && event.getEventType() < ArgoEventTypes.LAST_GENERATOR_EVENT) {
                if (listener instanceof ArgoGeneratorEventListener) {
                    handleFireGeneratorEvent((ArgoGeneratorEvent) event,
                            (ArgoGeneratorEventListener) listener);
                }
            }
        }
    }