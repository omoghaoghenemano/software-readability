protected AnnotationInstance parserPrimaryKeyJoinColumnList(List<JaxbPrimaryKeyJoinColumn> primaryKeyJoinColumnList, AnnotationTarget target) {
		if ( MockHelper.isNotEmpty( primaryKeyJoinColumnList ) ) {
			if ( primaryKeyJoinColumnList.size() == 1 ) {
				return parserPrimaryKeyJoinColumn( primaryKeyJoinColumnList.get( 0 ), target );
			}
			else {
				return create(
						PRIMARY_KEY_JOIN_COLUMNS,
						target,
						nestedPrimaryKeyJoinColumnList( "value", primaryKeyJoinColumnList, null )
				);
			}
		}

		return null;

	}