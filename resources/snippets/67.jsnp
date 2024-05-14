/**
		 * Build a ParameterMemento from the given parameter registration
		 *
		 * @param registration The parameter registration from a ProcedureCall
		 *
		 * @return The memento
		 */
		public static ParameterMemento fromRegistration(ParameterRegistrationImplementor registration) {
			return new ParameterMemento(
					registration.getPosition(),
					registration.getName(),
					registration.getMode(),
					registration.getType(),
					registration.getHibernateType()
			);
		}