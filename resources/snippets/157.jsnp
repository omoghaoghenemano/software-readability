public void buildModel() {
        if (getTarget() != null) {
            Object target = getTarget();
            Object kind = Model.getFacade().getAggregation(target);
            if (kind == null
                    || kind.equals(
                            Model.getAggregationKind().getNone())) {
                setSelected(ActionSetAssociationEndAggregation.NONE_COMMAND);
            } else {
		if (kind.equals(
		        Model.getAggregationKind().getAggregate())) {
		    setSelected(ActionSetAssociationEndAggregation
		            .AGGREGATE_COMMAND);
		} else {
		    if (kind.equals(
		            Model.getAggregationKind()
		            	.getComposite())) {
			setSelected(ActionSetAssociationEndAggregation
			        .COMPOSITE_COMMAND);
		    } else {
		        setSelected(ActionSetAssociationEndAggregation

			        .NONE_COMMAND);
		    }
		}
            }
        }
    }