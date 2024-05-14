/**
	 * Build a normal attribute.
	 *
	 * @param ownerType The descriptor of the attribute owner (aka declarer).
	 * @param property The Hibernate property descriptor for the attribute
	 * @param <X> The type of the owner
	 * @param <Y> The attribute type
	 *
	 * @return The built attribute descriptor or null if the attribute is not part of the JPA 2 model (eg backrefs)
	 */
	@SuppressWarnings({"unchecked"})
	public <X, Y> AttributeImplementor<X, Y> buildAttribute(AbstractManagedType<X> ownerType, Property property) {
		if ( property.isSynthetic() ) {
			// hide synthetic/virtual properties (fabricated by Hibernate) from the JPA metamodel.
			LOG.tracef( "Skipping synthetic property %s(%s)", ownerType.getTypeName(), property.getName() );
			return null;
		}
		LOG.trace( "Building attribute [" + ownerType.getTypeName() + "." + property.getName() + "]" );
		final AttributeContext<X> attributeContext = wrap( ownerType, property );
		final AttributeMetadata<X, Y> attributeMetadata =
				determineAttributeMetadata( attributeContext, normalMemberResolver );
		if ( attributeMetadata == null ) {
			return null;
		}
		if ( attributeMetadata.isPlural() ) {
			return buildPluralAttribute( (PluralAttributeMetadata) attributeMetadata );
		}
		final SingularAttributeMetadata<X, Y> singularAttributeMetadata = (SingularAttributeMetadata<X, Y>) attributeMetadata;
		final Type<Y> metaModelType = getMetaModelType( singularAttributeMetadata.getValueContext() );
		return new SingularAttributeImpl<X, Y>(
				attributeMetadata.getName(),
				attributeMetadata.getJavaType(),
				ownerType,
				attributeMetadata.getMember(),
				false,
				false,
				property.isOptional(),
				metaModelType,
				attributeMetadata.getPersistentAttributeType()
		);
	}
